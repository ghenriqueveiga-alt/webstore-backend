package com.hvs.webstore.back;

import com.hvs.webstore.back.infra.media.ChromaprintIntroDetector;
import com.hvs.webstore.back.infra.media.ChromaprintEndingDetector;
import com.hvs.webstore.back.infra.media.FFmpegVideoCutDetector;
import com.hvs.webstore.back.infra.media.FFprobeVideoDurationReader;
import com.hvs.webstore.back.infra.media.FingerprintCache;
import com.hvs.webstore.back.infra.media.SceneBoundaryVerifier;
import com.hvs.webstore.back.infra.media.VisualFusionIntroDetector;
import com.hvs.webstore.back.infra.media.VisualFusionEndingDetector;
import com.hvs.webstore.back.infra.media.VisualFusion;
import com.hvs.webstore.back.infra.media.MediaPathResolverImpl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class EscanearESalvarCortes {

    private static final double SPS_SECONDS_PER_FRAME = 0.387;
    private static final double MAX_SCAN_RATIO = 0.3;
    private static final int MIN_SCAN_SECONDS = 60;
    private static final long FINGERPRINT_TIMEOUT_MS = 45000L;

    public static void main(String[] args) throws Exception {
        final String url = "jdbc:mysql://localhost:3307/mysql-db?useUnicode=true&characterEncoding=utf8";
        final String user = "root";
        final String pass = "root";

        final com.hvs.webstore.back.app.service.MediaPathResolver resolver = new MediaPathResolverImpl("F:\\");

        final FFprobeVideoDurationReader durationReader = new FFprobeVideoDurationReader();
        final FingerprintCache fingerprintCache = new FingerprintCache();
        final ExecutorService threadPool = Executors.newFixedThreadPool(10, r -> {
            final Thread t = new Thread(r, "scan-" + r.hashCode());
            t.setDaemon(true);
            return t;
        });

        final FFmpegVideoCutDetector cutDetector = new FFmpegVideoCutDetector();
        final SceneBoundaryVerifier sceneVerifier = new SceneBoundaryVerifier(cutDetector);
        final VisualFusion fusion = new VisualFusion(durationReader);

        final ChromaprintIntroDetector chromaIntro = new ChromaprintIntroDetector(
                durationReader, threadPool, fingerprintCache, sceneVerifier);
        final ChromaprintEndingDetector chromaEnding = new ChromaprintEndingDetector(
                durationReader, 10, threadPool, fingerprintCache, sceneVerifier);

        final VisualFusionIntroDetector introDetector = new VisualFusionIntroDetector(chromaIntro, fusion, sceneVerifier);
        final VisualFusionEndingDetector endingDetector = new VisualFusionEndingDetector(chromaEnding, fusion, sceneVerifier);

        final Connection conn = DriverManager.getConnection(url, user, pass);
        conn.setAutoCommit(false);

        System.out.println("Lendo episodios do banco...");
        final List<EpisodioInfo> episodios = new ArrayList<>();
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(
                "SELECT e.id, e.arquivo_id, a.caminho, p.nome as programa_nome, e.numero, e.programa_id " +
                "FROM episodio e JOIN arquivo a ON a.id = e.arquivo_id JOIN programa p ON p.id = e.programa_id " +
                "WHERE a.caminho IS NOT NULL ORDER BY e.programa_id, e.numero")) {
            while (rs.next()) {
                episodios.add(new EpisodioInfo(
                        rs.getLong(1), rs.getLong(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getLong(6)));
            }
        }
        System.out.println("Total episodios: " + episodios.size());

        final Map<String, List<EpisodioInfo>> porDir = new LinkedHashMap<>();
        for (EpisodioInfo ep : episodios) {
            final String resolvido = resolver.resolve(ep.caminho);
            final java.nio.file.Path parentPath = java.nio.file.Paths.get(resolvido).getParent();
            if (parentPath != null) {
                porDir.computeIfAbsent(parentPath.toString(), k -> new ArrayList<>()).add(ep);
            }
        }
        System.out.println("Diretorios: " + porDir.size());

        final java.util.Set<Long> episodiosComCorte = new java.util.HashSet<>();
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery("SELECT DISTINCT episodio_id FROM corte")) {
            while (rs.next()) {
                episodiosComCorte.add(rs.getLong(1));
            }
        }
        System.out.println("Episodios ja com corte: " + episodiosComCorte.size());

        final java.util.Set<String> episodiosComCorteTipo = new java.util.HashSet<>();
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery("SELECT episodio_id, tipo_desc FROM corte")) {
            while (rs.next()) {
                episodiosComCorteTipo.add(rs.getLong(1) + "|" + rs.getString(2));
            }
        }
        System.out.println("Registros corte (ep+tipo): " + episodiosComCorteTipo.size());

        final String sqlCorte = "INSERT INTO corte (uuid, tipo_desc, status_desc, duracao, arquivo_id, episodio_id, inicio, fim) VALUES (?, ?, 'Active', ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sqlCorte)) {
            int totalIntros = 0;
            int totalEndings = 0;
            int totalComerciais = 0;
            int dirCount = 0;

            for (Map.Entry<String, List<EpisodioInfo>> entry : porDir.entrySet()) {
                dirCount++;
                final String dir = entry.getKey();
                final List<EpisodioInfo> grupo = entry.getValue();
                if (grupo.size() < 2) {
                    System.out.println("[" + dirCount + "/" + porDir.size() + "] " + grupo.size() + " ep(s) - pulando");
                    continue;
                }

                final boolean todosJaProcessados = grupo.stream().allMatch(ep -> episodiosComCorte.contains(ep.episodioId));
                if (todosJaProcessados) {
                    System.out.println("[" + dirCount + "/" + porDir.size() + "] " + grupo.size() + " ep(s) - ja processado");
                    continue;
                }

                System.out.print("[" + dirCount + "/" + porDir.size() + "] " + dir + " (" + grupo.size() + " eps)... ");

                final List<String> caminhos = new ArrayList<>();
                for (EpisodioInfo ep : grupo) {
                    caminhos.add(resolver.resolve(ep.caminho));
                }

                final long dirStart = System.currentTimeMillis();

                final Map<String, Long> duracoes = readDurationsParallel(caminhos, threadPool, durationReader);
                final Map<String, long[]> introFps = new ConcurrentHashMap<>();
                final Map<String, ChromaprintEndingDetector.Fingerprint> endingFps = new ConcurrentHashMap<>();
                generateBothFingerprints(caminhos, duracoes, threadPool, fingerprintCache, introFps, endingFps);

                final CompletableFuture<Map<String, com.hvs.webstore.back.app.service.IntroDetector.DetectedIntro>> introFuture =
                        CompletableFuture.supplyAsync(() -> {
                            VisualFusion.clearFrameCache();
                            return chromaIntro.detectIntroPrecomputed(caminhos, duracoes, introFps);
                        }, threadPool);

                final CompletableFuture<Map<String, com.hvs.webstore.back.app.service.EndingDetector.DetectedEnding>> endingFuture =
                        CompletableFuture.supplyAsync(() -> chromaEnding.detectEndingPrecomputed(caminhos, duracoes, endingFps), threadPool);

                CompletableFuture.allOf(introFuture, endingFuture).join();

                final Map<String, com.hvs.webstore.back.app.service.IntroDetector.DetectedIntro> intros = introFuture.get();
                final Map<String, com.hvs.webstore.back.app.service.EndingDetector.DetectedEnding> endings = endingFuture.get();

                int dirIntros = 0;
                int dirEndings = 0;
                int dirComerciais = 0;

                for (EpisodioInfo ep : grupo) {
                    final String camResolvido = resolver.resolve(ep.caminho);

                    final com.hvs.webstore.back.app.service.IntroDetector.DetectedIntro intro = intros.getOrDefault(camResolvido,
                            new com.hvs.webstore.back.app.service.IntroDetector.DetectedIntro(camResolvido, 0L, 0.0, 0.0, false));
                    final long introInicio = intro.aDetectado() ? intro.aInicioSegundos() : -1;
                    final long introFim = intro.aDetectado() ? intro.aInicioSegundos() + Math.round(intro.aDuracaoSegundos()) : -1;

                    if (intro.aDetectado() && intro.aDuracaoSegundos() >= 8.0 && intro.aConfianca() >= 0.65) {
                        final String chave = ep.episodioId + "|Abertura do Episódio";
                        if (!episodiosComCorteTipo.contains(chave)) {
                            final long inicio = intro.aInicioSegundos();
                            final long fim = inicio + Math.round(intro.aDuracaoSegundos());
                            ps.setString(1, UUID.randomUUID().toString());
                            ps.setString(2, "Abertura do Episódio");
                            ps.setString(3, formatDuration(intro.aDuracaoSegundos()));
                            ps.setLong(4, ep.arquivoId);
                            ps.setLong(5, ep.episodioId);
                            ps.setString(6, formatTime(inicio));
                            ps.setString(7, formatTime(fim));
                            ps.addBatch();
                            episodiosComCorteTipo.add(chave);
                            dirIntros++;
                            totalIntros++;
                        }
                    }

                    final com.hvs.webstore.back.app.service.EndingDetector.DetectedEnding ending = endings.getOrDefault(camResolvido,
                            new com.hvs.webstore.back.app.service.EndingDetector.DetectedEnding(camResolvido, 0L, 0.0, 0.0, false));
                    final long endingInicio = ending.aDetectado() ? ending.aInicioSegundos() : -1;
                    final long endingFim = ending.aDetectado() ? ending.aInicioSegundos() + Math.round(ending.aDuracaoSegundos()) : -1;

                    if (ending.aDetectado() && ending.aDuracaoSegundos() >= 8.0 && ending.aConfianca() >= 0.65) {
                        final String chave = ep.episodioId + "|Encerramento do Episódio";
                        if (!episodiosComCorteTipo.contains(chave)) {
                            final long inicio = ending.aInicioSegundos();
                            final long fim = inicio + Math.round(ending.aDuracaoSegundos());
                            ps.setString(1, UUID.randomUUID().toString());
                            ps.setString(2, "Encerramento do Episódio");
                            ps.setString(3, formatDuration(ending.aDuracaoSegundos()));
                            ps.setLong(4, ep.arquivoId);
                            ps.setLong(5, ep.episodioId);
                            ps.setString(6, formatTime(inicio));
                            ps.setString(7, formatTime(fim));
                            ps.addBatch();
                            episodiosComCorteTipo.add(chave);
                            dirEndings++;
                            totalEndings++;
                        }
                    }

                    final String chaveCo = ep.episodioId + "|Comercial/Propaganda";
                    if (!episodiosComCorteTipo.contains(chaveCo)) {
                        try {
                            final List<com.hvs.webstore.back.app.service.VideoCutDetector.DetectedCommercial> comerciais =
                                    cutDetector.detectCommercials(camResolvido);
                            final long duracaoSegundos = duracoes.getOrDefault(camResolvido, 0L);

                            for (com.hvs.webstore.back.app.service.VideoCutDetector.DetectedCommercial co : comerciais) {
                                final long coInicio = co.aInicio();
                                final long coFim = co.aFim();
                                final double coDuracao = co.aDuracaoSegundos();

                                if (coDuracao < 10.0) continue;
                                if (duracaoSegundos > 0 && coFim > duracaoSegundos - 10) continue;
                                if (introInicio >= 0 && coInicio < introFim + 5 && coFim > introInicio - 5) continue;
                                if (endingInicio >= 0 && coInicio < endingFim + 5 && coFim > endingInicio - 5) continue;

                                ps.setString(1, UUID.randomUUID().toString());
                                ps.setString(2, "Comercial/Propaganda");
                                ps.setString(3, formatDuration(coDuracao));
                                ps.setLong(4, ep.arquivoId);
                                ps.setLong(5, ep.episodioId);
                                ps.setString(6, formatTime(coInicio));
                                ps.setString(7, formatTime(coFim));
                                ps.addBatch();
                                episodiosComCorteTipo.add(chaveCo);
                                dirComerciais++;
                                totalComerciais++;
                                break;
                            }
                        } catch (Exception e) {
                            System.out.println("  Erro ao detectar comerciais: " + e.getMessage());
                        }
                    }
                }

                ps.executeBatch();
                conn.commit();
                final long dirElapsed = System.currentTimeMillis() - dirStart;
                System.out.println("intros=" + dirIntros + " endings=" + dirEndings + " comerciais=" + dirComerciais + " (" + dirElapsed + "ms)");
            }

            System.out.println("\n=== RESUMO FINAL ===");
            System.out.println("Intros:     " + totalIntros);
            System.out.println("Endings:    " + totalEndings);
            System.out.println("Comerciais: " + totalComerciais);
        }

        threadPool.shutdownNow();
        conn.close();
        System.out.println("Concluido.");
    }

    private static int scanSecondsFor(final long duracaoSegundos) {
        if (duracaoSegundos <= 0) return 240;
        final int adaptado = (int) Math.round(duracaoSegundos * MAX_SCAN_RATIO);
        return Math.max(MIN_SCAN_SECONDS, adaptado);
    }

    private static Map<String, Long> readDurationsParallel(
            final List<String> caminhos,
            final ExecutorService pool,
            final FFprobeVideoDurationReader reader) throws Exception {

        final Map<String, Long> duracoes = new ConcurrentHashMap<>();
        final List<Future<?>> futures = new ArrayList<>();
        for (final String caminho : caminhos) {
            futures.add(pool.submit(() -> duracoes.put(caminho, reader.readDurationSeconds(caminho))));
        }
        for (Future<?> f : futures) {
            f.get();
        }
        return duracoes;
    }

    private static void generateBothFingerprints(
            final List<String> caminhos,
            final Map<String, Long> duracoes,
            final ExecutorService pool,
            final FingerprintCache cache,
            final Map<String, long[]> introFps,
            final Map<String, ChromaprintEndingDetector.Fingerprint> endingFps) throws Exception {

        final List<Future<?>> futures = new ArrayList<>();
        for (final String caminho : caminhos) {
            futures.add(pool.submit(() -> {
                try {
                    final long dur = duracoes.getOrDefault(caminho, 0L);
                    final int introScanSec = scanSecondsFor(dur);
                    final int endingScanSec = scanSecondsFor(dur);

                    long[] introFp = null;
                    if (cache != null) {
                        introFp = cache.get(caminho, introScanSec, "intro");
                    }
                    if (introFp == null || introFp.length == 0) {
                        introFp = runFpgenPartial(caminho, 0, introScanSec);
                        if (cache != null && introFp.length > 0) {
                            cache.put(caminho, introScanSec, "intro", introFp);
                        }
                    }
                    introFps.put(caminho, introFp);

                    long[] endingFpRaw = null;
                    if (cache != null) {
                        endingFpRaw = cache.get(caminho, endingScanSec, "ending");
                    }
                    final long tailStart = Math.max(0L, dur - endingScanSec);
                    if (endingFpRaw == null || endingFpRaw.length == 0) {
                        endingFpRaw = runFpgenPartial(caminho, (int) tailStart, endingScanSec);
                        if (cache != null && endingFpRaw.length > 0) {
                            cache.put(caminho, endingScanSec, "ending", endingFpRaw);
                        }
                    }
                    endingFps.put(caminho, new ChromaprintEndingDetector.Fingerprint(endingFpRaw, tailStart));
                } catch (Exception e) {
                    introFps.put(caminho, new long[0]);
                    endingFps.put(caminho, new ChromaprintEndingDetector.Fingerprint(new long[0], 0L));
                }
            }));
        }
        for (Future<?> f : futures) {
            f.get();
        }
    }

    private static long[] runFpgenPartial(final String aCaminho, final int aStartSeconds, final int aDurationSeconds) throws Exception {
        final List<String> args = new ArrayList<>();
        args.add("ffmpeg");
        args.add("-nostdin");
        if (aStartSeconds > 0) {
            args.add("-ss");
            args.add(String.valueOf(aStartSeconds));
        }
        args.add("-i");
        args.add(aCaminho);
        args.add("-t");
        args.add(String.valueOf(aDurationSeconds));
        args.add("-vn");
        args.add("-ac");
        args.add("1");
        args.add("-ar");
        args.add("22050");
        args.add("-f");
        args.add("chromaprint");
        args.add("-fp_format");
        args.add("raw");
        args.add("-algorithm");
        args.add("2");
        args.add("-");

        final ProcessBuilder pb = new ProcessBuilder(args);
        pb.redirectError(ProcessBuilder.Redirect.DISCARD);
        final Process p = pb.start();

        final byte[] buf;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             InputStream is = p.getInputStream()) {
            final byte[] chunk = new byte[8192];
            int n;
            while ((n = is.read(chunk)) != -1 && n > 0) {
                bos.write(chunk, 0, n);
            }
            if (!p.waitFor(FINGERPRINT_TIMEOUT_MS, TimeUnit.MILLISECONDS)) {
                p.destroyForcibly();
                return new long[0];
            }
            buf = bos.toByteArray();
        }

        final int nFrames = buf.length / 4;
        final long[] fp = new long[nFrames];
        for (int i = 0; i < nFrames; i++) {
            final long b0 = buf[i * 4] & 0xFF;
            final long b1 = buf[i * 4 + 1] & 0xFF;
            final long b2 = buf[i * 4 + 2] & 0xFF;
            final long b3 = buf[i * 4 + 3] & 0xFF;
            fp[i] = (b3 << 24) | (b2 << 16) | (b1 << 8) | b0;
        }
        return fp;
    }

    private static String formatDuration(double seconds) {
        long total = Math.round(seconds);
        long h = total / 3600;
        long m = (total % 3600) / 60;
        long s = total % 60;
        return String.format("%02d:%02d:%02d", h, m, s);
    }

    private static String formatTime(long seconds) {
        long h = seconds / 3600;
        long m = (seconds % 3600) / 60;
        long s = seconds % 60;
        return String.format("%02d:%02d:%02d", h, m, s);
    }

    private record EpisodioInfo(long episodioId, long arquivoId, String caminho, String programaNome, int numero, long programaId) {}
}
