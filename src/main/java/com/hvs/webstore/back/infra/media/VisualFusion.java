package com.hvs.webstore.back.infra.media;

import com.hvs.webstore.back.app.service.VideoDurationReader;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class VisualFusion {

    private static final Logger LOG = Logger.getLogger(VisualFusion.class.getName());

    private static final int W = 64;
    private static final int H = 48;
    private static final int FRAME_BYTES = W * H;
    private static final double THRESHOLD = 0.70;
    private static final double DUPLICATE_THRESHOLD = 0.995;
    private static final double MIN_VALID_DURATION = 8.0;
    private static final long FRAME_TIMEOUT_MS = 20000L;
    private static final long START_ROUND = 10L;
    private static final long DURATION_ROUND = 5L;
    private static final int MAX_DUPLICATE_CHECK = 50;
    private static final ExecutorService POOL = Executors.newFixedThreadPool(8, r -> {
        final Thread t = new Thread(r, "visualfusion");
        t.setDaemon(true);
        return t;
    });

    private static final ConcurrentHashMap<String, byte[]> FRAME_CACHE = new ConcurrentHashMap<>();

    public static void clearFrameCache() {
        FRAME_CACHE.clear();
    }

    public static int getFrameCacheSize() {
        return FRAME_CACHE.size();
    }

    private final VideoDurationReader durationReader;
    private final Map<String, Long> duracaoCache = new HashMap<>();

    public VisualFusion() {
        this(new FFprobeVideoDurationReader());
    }

    public VisualFusion(final VideoDurationReader aDurationReader) {
        this.durationReader = aDurationReader;
    }

    public List<VisualCandidato> aplicar(final List<VisualCandidato> aCandidatos) {

        final List<VisualCandidato> naoDetectados = new ArrayList<>();
        final Map<String, List<VisualCandidato>> porDiretorio = new TreeMap<>();
        for (VisualCandidato c : aCandidatos) {
            if (c.detectado()) {
                porDiretorio.computeIfAbsent(parentDir(c.caminho()), k -> new ArrayList<>()).add(c);
            } else {
                naoDetectados.add(c);
            }
        }

        final List<VisualCandidato> saida = new ArrayList<>(naoDetectados);
        for (List<VisualCandidato> grupo : porDiretorio.values()) {
            final Map<String, List<VisualCandidato>> subgrupos = new LinkedHashMap<>();
            for (VisualCandidato c : grupo) {
                final String chave = roundTo(c.inicio(), START_ROUND) + "|" + roundTo(c.dur(), DURATION_ROUND);
                subgrupos.computeIfAbsent(chave, k -> new ArrayList<>()).add(c);
            }
            for (List<VisualCandidato> subgrupo : subgrupos.values()) {
                this.processarSubgrupo(subgrupo, saida);
            }
        }
        return saida;
    }

    private void processarSubgrupo(final List<VisualCandidato> aSubgrupo, final List<VisualCandidato> aSaida) {

        if (aSubgrupo.size() < 2) {
            aSaida.addAll(aSubgrupo);
            return;
        }
        if (this.saoMuitoSemelhantes(aSubgrupo)) {
            aSaida.add(aSubgrupo.get(0));
            return;
        }
        final VisualCandidato ref = aSubgrupo.stream()
                .min(Comparator.comparing(VisualCandidato::caminho)).orElse(aSubgrupo.get(0));
        final double visScore = this.scoreBloco(ref, aSubgrupo);
        for (VisualCandidato c : aSubgrupo) {
            if (visScore < THRESHOLD) {
                final double penalty = Math.max(0.70, visScore);
                final double confFinal = Math.min(1.0, 0.6 * c.conf() + 0.4 * penalty);
                if (confFinal < 0.50) {
                    aSaida.add(c.invalido());
                } else {
                    aSaida.add(new VisualCandidato(c.caminho(), c.inicio(), c.dur(), confFinal, true));
                }
            } else {
                aSaida.add(c.comConfiancaRefinada(visScore));
            }
        }
    }

    private double scoreBloco(final VisualCandidato aRef, final List<VisualCandidato> aMembros) {

        final double dur = aRef.dur();
        if (dur < MIN_VALID_DURATION) {
            return 1.0;
        }
        final long[] offsets = new long[]{
                5L,
                Math.max(5L, Math.round(dur / 4.0)),
                Math.max(5L, Math.round(dur / 2.0)),
                Math.max(5L, Math.round(dur * 3.0 / 4.0)),
                Math.max(5L, Math.round(dur) - 8L)
        };

        final List<String> uniquePaths = new ArrayList<>();
        uniquePaths.add(aRef.caminho());
        for (VisualCandidato c : aMembros) {
            if (!c.caminho().equals(aRef.caminho()) && !uniquePaths.contains(c.caminho())) {
                uniquePaths.add(c.caminho());
            }
        }

        final Map<String, byte[][]> allFrames = new HashMap<>();
        final List<Future<?>> futures = new ArrayList<>();
        for (String path : uniquePaths) {
            final VisualCandidato cand = aMembros.stream()
                    .filter(c -> c.caminho().equals(path))
                    .findFirst().orElse(aRef);
            final long[] absOffsets = deslocar(cand.inicio(), offsets);
            futures.add(POOL.submit(() -> allFrames.put(path, framesMulti(path, absOffsets))));
        }
        for (Future<?> f : futures) {
            try {
                f.get();
            } catch (Exception ignored) {
            }
        }

        final byte[][] refFrames = allFrames.getOrDefault(aRef.caminho(), new byte[offsets.length][]);
        final double[] pontuacoes = new double[aMembros.size() * offsets.length];
        int n = 0;
        for (VisualCandidato outro : aMembros) {
            if (outro.caminho().equals(aRef.caminho())) {
                continue;
            }
            final byte[][] outFrames = allFrames.getOrDefault(outro.caminho(), new byte[offsets.length][]);
            for (int i = 0; i < offsets.length; i++) {
                if (refFrames[i] == null || outFrames[i] == null) {
                    continue;
                }
                pontuacoes[n++] = score(refFrames[i], outFrames[i]);
            }
        }
        if (n < 3) {
            return 1.0;
        }
        final double[] val = Arrays.copyOf(pontuacoes, n);
        Arrays.sort(val);
        return val[val.length / 2];
    }

    private static byte[][] framesMulti(final String aCaminho, final long[] aTempos) {
        try {
            final StringBuilder selectExpr = new StringBuilder();
            for (int i = 0; i < aTempos.length; i++) {
                if (i > 0) {
                    selectExpr.append("+");
                }
                selectExpr.append("between(t,").append(aTempos[i]).append(",").append(aTempos[i] + 0.1).append(")");
            }
            final Process p = new ProcessBuilder(
                    "ffmpeg", "-nostdin",
                    "-i", aCaminho,
                    "-vf", "select='" + selectExpr + "',scale=" + W + ":" + H + ",format=gray",
                    "-vsync", "vfr",
                    "-f", "rawvideo", "-")
                    .redirectError(ProcessBuilder.Redirect.DISCARD)
                    .start();
            final byte[] buf;
            try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
                 InputStream is = p.getInputStream()) {
                final byte[] chunk = new byte[8192];
                int n;
                while ((n = is.read(chunk)) != -1 && n > 0) {
                    bos.write(chunk, 0, n);
                }
                if (!p.waitFor(FRAME_TIMEOUT_MS * aTempos.length, TimeUnit.MILLISECONDS)) {
                    p.destroyForcibly();
                    return new byte[aTempos.length][];
                }
                buf = bos.toByteArray();
            }
            final byte[][] frames = new byte[aTempos.length][];
            final int totalFrames = buf.length / FRAME_BYTES;
            for (int i = 0; i < Math.min(totalFrames, aTempos.length); i++) {
                frames[i] = Arrays.copyOfRange(buf, i * FRAME_BYTES, (i + 1) * FRAME_BYTES);
            }
            return frames;
        } catch (Exception e) {
            final byte[][] fallback = new byte[aTempos.length][];
            for (int i = 0; i < aTempos.length; i++) {
                fallback[i] = frame(aCaminho, aTempos[i]);
            }
            return fallback;
        }
    }

    private static long[] deslocar(final long aInicio, final long[] aOffsets) {

        final long[] abs = new long[aOffsets.length];
        for (int i = 0; i < aOffsets.length; i++) {
            abs[i] = aInicio + aOffsets[i];
        }
        return abs;
    }

    private boolean saoMuitoSemelhantes(final List<VisualCandidato> aGrupo) {

        if (aGrupo.size() <= 1) {
            return false;
        }

        final int n = aGrupo.size();
        final boolean[] visited = new boolean[n];
        int componentCount = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            componentCount++;
            if (componentCount > 1) {
                return false;
            }
            final java.util.Queue<Integer> queue = new java.util.LinkedList<>();
            queue.add(i);
            visited[i] = true;
            while (!queue.isEmpty()) {
                final int curr = queue.poll();
                int checkLimit = Math.min(n, MAX_DUPLICATE_CHECK);
                for (int j = 0; j < checkLimit; j++) {
                    if (j == curr || visited[j]) {
                        continue;
                    }
                    if (this.saoDuplicatas(aGrupo.get(curr), aGrupo.get(j))) {
                        visited[j] = true;
                        queue.add(j);
                    }
                }
            }
        }
        return componentCount == 1;
    }

    private boolean saoDuplicatas(final VisualCandidato a, final VisualCandidato b) {

        final long da = this.duracao(a.caminho());
        final long db = this.duracao(b.caminho());
        if (da <= 0L || db <= 0L || Math.abs(da - db) > 6L) {
            return false;
        }
        final long[] tempos = new long[]{10L, Math.min(da / 2L, 600L), Math.max(6L, da - 10L)};
        try {
            final java.util.concurrent.Future<byte[][]> fa = POOL.submit(() -> framesPontos(a.caminho(), tempos));
            final java.util.concurrent.Future<byte[][]> fb = POOL.submit(() -> framesPontos(b.caminho(), tempos));
            final byte[][] af = fa.get();
            final byte[][] bf = fb.get();
            for (int i = 0; i < tempos.length; i++) {
                if (af[i] == null || bf[i] == null) {
                    return false;
                }
                if (score(af[i], bf[i]) < DUPLICATE_THRESHOLD) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static byte[][] framesPontos(final String aCaminho, final long[] aPontos) {

        final byte[][] frames = new byte[aPontos.length][];
        final List<java.util.concurrent.Future<?>> futures = new ArrayList<>();
        for (int i = 0; i < aPontos.length; i++) {
            final int idx = i;
            futures.add(POOL.submit(() -> frames[idx] = frame(aCaminho, aPontos[idx])));
        }
        for (java.util.concurrent.Future<?> f : futures) {
            try {
                f.get();
            } catch (Exception ignored) {
            }
        }
        return frames;
    }

    private long duracao(final String aCaminho) {

        final Long cache = this.duracaoCache.get(aCaminho);
        if (cache != null) {
            return cache;
        }
        final long d = this.durationReader.readDurationSeconds(aCaminho);
        this.duracaoCache.put(aCaminho, d);
        return d;
    }

    private static long roundTo(final long aValor, final long aGranularidade) {
        return (long) Math.floor(aValor / (double) aGranularidade) * aGranularidade;
    }

    private static long roundTo(final double aValor, final long aGranularidade) {
        return (long) Math.floor(aValor / aGranularidade) * aGranularidade;
    }

    private static String parentDir(final String aCaminho) {

        final Path p = Paths.get(aCaminho);
        return p.getParent() != null ? p.getParent().toString() : "";
    }

    private static byte[] frame(final String aCaminho, final long aSegundo) {

        final String cacheKey = aCaminho + ":" + aSegundo;
        final byte[] cached = FRAME_CACHE.get(cacheKey);
        if (cached != null) {
            return cached;
        }
        try {
            final Process p = new ProcessBuilder(
                    "ffmpeg", "-nostdin", "-ss", String.valueOf(aSegundo),
                    "-i", aCaminho, "-frames:v", "1",
                    "-vf", "scale=" + W + ":" + H + ",format=gray",
                    "-f", "rawvideo", "-")
                    .redirectError(ProcessBuilder.Redirect.DISCARD)
                    .start();
            final byte[] buf;
            try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
                 InputStream is = p.getInputStream()) {
                final byte[] chunk = new byte[8192];
                int n;
                while ((n = is.read(chunk)) != -1 && n > 0) {
                    bos.write(chunk, 0, n);
                }
                if (!p.waitFor(FRAME_TIMEOUT_MS, TimeUnit.MILLISECONDS)) {
                    p.destroyForcibly();
                    return null;
                }
                buf = bos.toByteArray();
            }
            final byte[] result = buf.length >= FRAME_BYTES ? Arrays.copyOf(buf, FRAME_BYTES) : null;
            if (result != null) {
                FRAME_CACHE.put(cacheKey, result);
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    private static double score(final byte[] a, final byte[] b) {

        long soma = 0L;
        for (int i = 0; i < FRAME_BYTES; i++) {
            final int d = (a[i] & 0xFF) - (b[i] & 0xFF);
            soma += d < 0 ? -d : d;
        }
        return Math.max(0.0, 1.0 - (soma / (double) FRAME_BYTES) / 255.0);
    }

    public record VisualCandidato(String caminho, long inicio, double dur, double conf, boolean detectado) {

        public VisualCandidato invalido() {
            return new VisualCandidato(this.caminho, 0L, 0.0, 0.0, false);
        }

        public VisualCandidato comConfiancaRefinada(final double aVisScore) {
            final double confFinal = Math.min(1.0, 0.6 * this.conf + 0.4 * aVisScore);
            return new VisualCandidato(this.caminho, this.inicio, this.dur, confFinal, true);
        }
    }
}
