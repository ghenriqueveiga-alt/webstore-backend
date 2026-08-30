package com.hvs.webstore.back.infra.media;

import com.hvs.webstore.back.app.service.EndingDetector;
import com.hvs.webstore.back.app.service.VideoDurationReader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ChromaprintEndingDetector implements EndingDetector {

    private static final Logger LOG = Logger.getLogger(ChromaprintEndingDetector.class.getName());

    private static final long FINGERPRINT_TIMEOUT_MS = 45000L;
    private static final double SPS_SECONDS_PER_FRAME = 0.387;
    private static final int MIN_WINDOW_SECONDS = 15;
    private static final int MAX_WINDOW_SECONDS = 90;
    private static final int WINDOW_STEP_SECONDS = 15;
    private static final int PASSOS_AVALIADOS = 12;
    private static final int MIN_FRAMES = 40;
    private static final int FINE_TUNE_RADIUS_FRAMES = 2;
    private static final int FINE_TUNE_EDGE_FRAMES = 12;
    private static final double OPENING_BITS_THRESHOLD = 8.5;
    private static final double CONFIDENCE_THRESHOLD = 0.65;
    private static final int DURATION_SLIDE_FRAMES = 12;
    private static final int FIM_SUSTENTADO_FRAMES = 40;
    private static final int MAX_CLUSTERS_PER_GROUP = 64;
    private static final int END_TAIL_MARGIN_FRAMES = 45;
    private static final double MAX_SCAN_RATIO = 0.3;
    private static final int MIN_SCAN_SECONDS = 60;
    private static final int BATCH_SIZE = 200;

    private final VideoDurationReader videoDurationReader;
    private final int threadCount;
    private final ExecutorService threadPool;
    private final FingerprintCache fingerprintCache;
    private final SceneBoundaryVerifier sceneVerifier;
    private final DetectionLogger detectionLogger;

    public ChromaprintEndingDetector(final VideoDurationReader aVideoDurationReader) {
        this(aVideoDurationReader, 0, null, null, null);
    }

    public ChromaprintEndingDetector(final VideoDurationReader aVideoDurationReader, final int aThreadCount) {
        this(aVideoDurationReader, aThreadCount, null, null, null);
    }

    public ChromaprintEndingDetector(final VideoDurationReader aVideoDurationReader, final int aThreadCount,
                                     final ExecutorService aThreadPool,
                                     final FingerprintCache aCache,
                                     final SceneBoundaryVerifier aSceneVerifier) {
        this.videoDurationReader = aVideoDurationReader;
        this.threadCount = aThreadCount > 0
                ? aThreadCount
                : Math.max(2, (int) Math.round(Runtime.getRuntime().availableProcessors() * 0.6));
        this.threadPool = aThreadPool;
        this.fingerprintCache = aCache;
        this.sceneVerifier = aSceneVerifier;
        this.detectionLogger = new DetectionLogger();
    }

    private int scanSecondsFor(final long duracaoSegundos) {
        if (duracaoSegundos <= 0) {
            return 240;
        }
        final int adaptado = (int) Math.round(duracaoSegundos * MAX_SCAN_RATIO);
        return Math.max(MIN_SCAN_SECONDS, adaptado);
    }

    @Override
    public Map<String, DetectedEnding> detectEnding(final List<String> aCaminhos) {

        final List<String> valid = validate(aCaminhos);
        final Map<String, DetectedEnding> result = new LinkedHashMap<>();
        if (valid.size() < 2) {
            for (String caminho : valid) {
                result.put(caminho, new DetectedEnding(caminho, 0L, 0.0, 0.0, false));
            }
            return result;
        }

        final long t0 = System.currentTimeMillis();
        for (int batchStart = 0; batchStart < valid.size(); batchStart += BATCH_SIZE) {
            final int batchEnd = Math.min(batchStart + BATCH_SIZE, valid.size());
            detectBatch(valid.subList(batchStart, batchEnd), result);
        }
        final long elapsed = System.currentTimeMillis() - t0;
        detectionLogger.printSummary("ENDING");
        LOG.info("Ending detection completed: " + valid.size() + " files in " + elapsed + "ms");
        return result;
    }

    public Map<String, DetectedEnding> detectEndingPrecomputed(
            final List<String> aCaminhos,
            final Map<String, Long> aDuracoes,
            final Map<String, Fingerprint> aFingerprints) {

        final List<String> valid = validate(aCaminhos);
        final Map<String, DetectedEnding> result = new LinkedHashMap<>();
        if (valid.size() < 2) {
            for (String caminho : valid) {
                result.put(caminho, new DetectedEnding(caminho, 0L, 0.0, 0.0, false));
            }
            return result;
        }

        final long t0 = System.currentTimeMillis();
        for (int batchStart = 0; batchStart < valid.size(); batchStart += BATCH_SIZE) {
            final int batchEnd = Math.min(batchStart + BATCH_SIZE, valid.size());
            final List<String> batch = valid.subList(batchStart, batchEnd);
            final Map<String, List<String>> grupos = groupByParentDirectory(batch);
            for (Map.Entry<String, List<String>> entrada : grupos.entrySet()) {
                final List<String> grupo = entrada.getValue();
                if (grupo.size() < 2) {
                    for (String caminho : grupo) {
                        result.put(caminho, new DetectedEnding(caminho, 0L, 0.0, 0.0, false));
                    }
                    continue;
                }
                processGroup(aFingerprints, grupo, result, aDuracoes);
            }
            for (String caminho : batch) {
                result.putIfAbsent(caminho, new DetectedEnding(caminho, 0L, 0.0, 0.0, false));
            }
        }
        final long elapsed = System.currentTimeMillis() - t0;
        detectionLogger.printSummary("ENDING");
        LOG.info("Ending detection (precomputed) completed: " + valid.size() + " files in " + elapsed + "ms");
        return result;
    }

    private List<String> validate(final List<String> aCaminhos) {
        final List<String> valid = new ArrayList<>();
        if (aCaminhos != null) {
            for (String caminho : aCaminhos) {
                if (caminho != null && !caminho.isBlank() && Files.exists(Paths.get(caminho))) {
                    valid.add(caminho);
                }
            }
        }
        return valid;
    }

    private void detectBatch(final List<String> aBatch, final Map<String, DetectedEnding> aResult) {
        final Map<String, Long> duracoes = lerDuracoes(aBatch);
        final Map<String, Fingerprint> fingerprints = generateFingerprints(aBatch, duracoes);
        final Map<String, List<String>> grupos = groupByParentDirectory(aBatch);

        for (Map.Entry<String, List<String>> entrada : grupos.entrySet()) {
            final List<String> grupo = entrada.getValue();
            if (grupo.size() < 2) {
                for (String caminho : grupo) {
                    aResult.put(caminho, new DetectedEnding(caminho, 0L, 0.0, 0.0, false));
                }
                continue;
            }
            this.processGroup(fingerprints, grupo, aResult, duracoes);
        }
        for (String caminho : aBatch) {
            aResult.putIfAbsent(caminho, new DetectedEnding(caminho, 0L, 0.0, 0.0, false));
        }
    }

    private Map<String, Long> lerDuracoes(final List<String> aCaminhos) {
        final Map<String, Long> duracoes = new HashMap<>();
        for (String caminho : aCaminhos) {
            duracoes.put(caminho, this.videoDurationReader.readDurationSeconds(caminho));
        }
        return duracoes;
    }

    private void processGroup(final Map<String, Fingerprint> aFps,
                              final List<String> aGrupo,
                              final Map<String, DetectedEnding> aResultado,
                              final Map<String, Long> aDuracoes) {

        final List<String> grupo = aGrupo.stream().sorted(Comparator.naturalOrder()).toList();
        final Map<String, Fingerprint> fps = new HashMap<>();
        final List<String> comFp = new ArrayList<>();
        for (String caminho : grupo) {
            final Fingerprint f = aFps.get(caminho);
            if (f != null && f.frames().length > 0) {
                fps.put(caminho, f);
                comFp.add(caminho);
            } else {
                aResultado.put(caminho, new DetectedEnding(caminho, 0L, 0.0, 0.0, false));
            }
        }
        if (fps.size() < 2) {
            for (String caminho : fps.keySet()) {
                aResultado.put(caminho, new DetectedEnding(caminho, 0L, 0.0, 0.0, false));
            }
            return;
        }

        int clusters = 0;
        int tentativas = 0;
        while (fps.size() >= 2 && clusters < MAX_CLUSTERS_PER_GROUP && tentativas < MAX_CLUSTERS_PER_GROUP * 2) {
            tentativas++;
            final String refNome = fps.keySet().stream().min(String::compareTo).get();
            final long[] ref = fps.get(refNome).frames();
            final Ending best = this.findBestEnding(ref, fps);

            final List<String> cluster = new ArrayList<>();
            final Map<String, Integer> starts = new HashMap<>();
            final Map<String, Double> confidences = new HashMap<>();
            cluster.add(refNome);
            starts.put(refNome, best.refStart);
            for (String caminho : comFp) {
                if (caminho.equals(refNome) || !fps.containsKey(caminho)) {
                    continue;
                }
                final long[] fp = fps.get(caminho).frames();
                final Match m = this.findBestWindow(ref, best.refStart, fp, best.windowFrames);
                final int inicioFrame = this.fineTuneStart(ref, best.refStart, fp, m.frame, best.windowFrames);
                final double avgBits = this.avgBits(ref, best.refStart, fp, inicioFrame, best.windowFrames);
                final double confidence = Math.max(0.0, 1.0 - avgBits / 32.0);
                final int win = Math.min(Math.min(best.windowFrames, ref.length - best.refStart), fp.length - inicioFrame);
                if (win >= MIN_FRAMES && avgBits < OPENING_BITS_THRESHOLD && confidence >= CONFIDENCE_THRESHOLD) {
                    cluster.add(caminho);
                    starts.put(caminho, inicioFrame);
                    confidences.put(caminho, confidence);
                }
            }

            if (cluster.size() < 2) {
                aResultado.put(refNome, new DetectedEnding(refNome, 0L, 0.0, 0.0, false));
                fps.remove(refNome);
                continue;
            }

            final Map<String, Integer> startsDuracao = new HashMap<>(starts);
            startsDuracao.remove(refNome);
            final double durSeg = this.exactDurationSeconds(ref, best, startsDuracao, fps);
            for (String caminho : cluster) {
                fps.remove(caminho);
            }

            final Fingerprint refFp = aFps.get(refNome);
            final long refInicioAbs = refFp.tailStartSeconds()
                    + Math.round(best.refStart * SPS_SECONDS_PER_FRAME);

            boolean sceneVerified = true;
            if (sceneVerifier != null) {
                sceneVerified = sceneVerifier.verifyEndingBoundary(refNome, refInicioAbs, Math.round(durSeg));
            }

            long previewCutoff = -1L;
            if (sceneVerifier != null) {
                final long refDuration = aDuracoes.getOrDefault(refNome, 0L);
                if (refDuration > 0) {
                    previewCutoff = sceneVerifier.detectPreviewStart(refNome, refDuration);
                }
            }

            for (String caminho : cluster) {
                final boolean isRef = caminho.equals(refNome);
                final Fingerprint f = aFps.get(caminho);
                final long inicioRel = isRef ? best.refStart : starts.get(caminho);
                final long inicioAbs = f.tailStartSeconds() + Math.round(inicioRel * SPS_SECONDS_PER_FRAME);
                final double conf = isRef ? best.confidence : confidences.get(caminho);

                boolean detected = sceneVerified && conf >= CONFIDENCE_THRESHOLD;

                if (detected && previewCutoff > 0 && inicioAbs + Math.round(durSeg) > previewCutoff) {
                    final long newDur = Math.max(0, previewCutoff - inicioAbs);
                    if (newDur < Math.round(durSeg * 0.5)) {
                        detected = false;
                    }
                }

                aResultado.put(caminho, new DetectedEnding(caminho,
                        Math.max(0L, inicioAbs),
                        durSeg,
                        detected ? conf : 0.0,
                        detected));
            }
            clusters++;
        }
        for (String caminho : fps.keySet()) {
            if (!aResultado.containsKey(caminho)) {
                aResultado.put(caminho, new DetectedEnding(caminho, 0L, 0.0, 0.0, false));
            }
        }
    }

    private Ending findBestEnding(final long[] ref, final Map<String, Fingerprint> fps) {

        final Map<String, long[]> frames = new HashMap<>();
        for (Map.Entry<String, Fingerprint> e : fps.entrySet()) {
            frames.put(e.getKey(), e.getValue().frames());
        }

        Ending melhor = null;
        for (int janelaSeg : janelasCandidatas()) {
            final int janelaFrames = (int) Math.round(janelaSeg / SPS_SECONDS_PER_FRAME);
            if (janelaFrames > ref.length) {
                continue;
            }
            final int passo = Math.max(1, (int) Math.round(janelaFrames / (double) PASSOS_AVALIADOS));
            double melhorSim = -1.0;
            int melhorInicio = 0;
            final int primeiroInicio = Math.max(0, ref.length - janelaFrames - END_TAIL_MARGIN_FRAMES);
            int inicio = primeiroInicio;
            while (inicio + janelaFrames <= ref.length) {
                final double sim = this.averageSimilarity(ref, inicio, janelaFrames, frames);
                if (sim > melhorSim) {
                    melhorSim = sim;
                    melhorInicio = inicio;
                }
                inicio += passo;
            }
            final int refStartLo = Math.max(0, melhorInicio - passo);
            final int refStartHi = Math.min(ref.length - janelaFrames, melhorInicio + passo);
            for (int s = refStartLo; s <= refStartHi; s++) {
                final double sim = this.averageSimilarity(ref, s, janelaFrames, frames);
                if (sim > melhorSim) {
                    melhorSim = sim;
                    melhorInicio = s;
                }
            }
            final Ending candidato = new Ending(melhorInicio, janelaFrames,
                    Math.max(0.0, melhorSim),
                    melhorSim >= CONFIDENCE_THRESHOLD,
                    janelaFrames * SPS_SECONDS_PER_FRAME,
                    (int) Math.round(this.countMatches(ref, melhorInicio, janelaFrames, frames)));
            if (melhor == null || candidato.confidence > melhor.confidence) {
                melhor = candidato;
            }
        }
        return melhor != null ? melhor : new Ending(0, 0, 0.0, false, 0.0, 0);
    }

    private double exactDurationSeconds(final long[] ref, final Ending best,
                                        final Map<String, Integer> aStarts,
                                        final Map<String, Fingerprint> fps) {

        final List<Integer> duracoesFrames = new ArrayList<>();
        for (Map.Entry<String, Integer> e : aStarts.entrySet()) {
            final long[] fp = fps.get(e.getKey()).frames();
            final int start = e.getValue();
            final double bitsInicio = this.avgBits(ref, best.refStart, fp, start, MIN_FRAMES);
            if (bitsInicio >= OPENING_BITS_THRESHOLD) {
                continue;
            }
            final int endFrame = this.findExactEndFrame(ref, best.refStart, fp, start);
            duracoesFrames.add(endFrame - start);
        }
        if (!best.detectado || duracoesFrames.isEmpty()) {
            return 0.0;
        }
        int maior = 0;
        for (int d : duracoesFrames) {
            if (d > maior) {
                maior = d;
            }
        }
        if (maior < MIN_FRAMES) {
            return 0.0;
        }
        return maior * SPS_SECONDS_PER_FRAME;
    }

    private int findExactEndFrame(final long[] ref, final int refStart,
                                  final long[] fp, final int start) {

        final int maxLen = Math.min(ref.length - refStart, fp.length - start);
        final int slide = Math.min(DURATION_SLIDE_FRAMES, Math.max(1, maxLen));
        if (slide < 1 || refStart + slide > ref.length || start + slide > fp.length) {
            return start;
        }
        long sum = 0L;
        for (int i = 0; i < slide; i++) {
            sum += Long.bitCount(ref[refStart + i] ^ fp[start + i]);
        }
        int lastGoodEnd = start + slide;
        int divergenciasConsecutivas = 0;
        for (int i = slide; i < maxLen; i++) {
            final double avg = (double) sum / slide;
            if (avg >= OPENING_BITS_THRESHOLD) {
                divergenciasConsecutivas++;
                if (divergenciasConsecutivas >= FIM_SUSTENTADO_FRAMES) {
                    break;
                }
            } else {
                divergenciasConsecutivas = 0;
                lastGoodEnd = start + i + 1;
            }
            sum -= Long.bitCount(ref[refStart + i - slide] ^ fp[start + i - slide]);
            sum += Long.bitCount(ref[refStart + i] ^ fp[start + i]);
        }
        if (lastGoodEnd - start >= MIN_FRAMES && lastGoodEnd < start + maxLen) {
            final int probe = Math.min(FINE_TUNE_EDGE_FRAMES, maxLen - (lastGoodEnd - start));
            if (probe >= 1) {
                int best = lastGoodEnd;
                double bestBits = Double.MAX_VALUE;
                for (int delta = -1; delta <= 1; delta++) {
                    final int candidate = lastGoodEnd + delta;
                    if (candidate - start < MIN_FRAMES || candidate + probe > start + maxLen
                            || candidate + probe > fp.length) {
                        continue;
                    }
                    final double bits = this.avgBits(ref, refStart, fp, candidate, probe);
                    if (bits < bestBits) {
                        bestBits = bits;
                        best = candidate;
                    }
                }
                return best;
            }
        }
        return lastGoodEnd;
    }

    private double avgBits(final long[] ref, final int refStart,
                           final long[] fp, final int start, final int janelaFrames) {

        final int win = Math.min(Math.min(janelaFrames, ref.length - refStart), fp.length - start);
        long bits = 0L;
        for (int i = 0; i < win; i++) {
            bits += Long.bitCount(ref[refStart + i] ^ fp[start + i]);
        }
        return (double) bits / Math.max(1, win);
    }

    private double countMatches(final long[] ref, final int refStart,
                                final int janelaFrames, final Map<String, long[]> fps) {

        double count = 0.0;
        for (long[] fp : fps.values()) {
            final Match m = this.findBestWindow(ref, refStart, fp, janelaFrames);
            if (m.win >= MIN_FRAMES && m.avgBits < OPENING_BITS_THRESHOLD) {
                count++;
            }
        }
        return count;
    }

    private List<Integer> janelasCandidatas() {

        final List<Integer> janelas = new ArrayList<>();
        for (int s = MIN_WINDOW_SECONDS; s <= MAX_WINDOW_SECONDS; s += WINDOW_STEP_SECONDS) {
            janelas.add(s);
        }
        return janelas;
    }

    private double averageSimilarity(final long[] ref, final int refStart,
                                     final int janelaFrames, final Map<String, long[]> fps) {

        double total = 0.0;
        int count = 0;
        for (long[] fp : fps.values()) {
            final Match m = this.findBestWindow(ref, refStart, fp, janelaFrames);
            if (m.win < MIN_FRAMES) {
                continue;
            }
            total += m.avgBits;
            count++;
        }
        if (count == 0) {
            return 0.0;
        }
        final double avgBits = total / count;
        return 1.0 - avgBits / 32.0;
    }

    private Match findBestWindow(final long[] ref, final int refStart,
                                 final long[] fp, final int janelaFrames) {

        final int win = Math.min(Math.min(janelaFrames, ref.length - refStart), fp.length);
        final int maxStart = Math.max(0, fp.length - win);
        long bestBits = Long.MAX_VALUE;
        int best = 0;
        if (win >= MIN_FRAMES) {
            for (int start = 0; start <= maxStart; start++) {
                long bits = 0L;
                for (int i = 0; i < win; i++) {
                    bits += Long.bitCount(ref[refStart + i] ^ fp[start + i]);
                }
                if (bits < bestBits) {
                    bestBits = bits;
                    best = start;
                }
            }
        }
        final double avg = (double) bestBits / (double) Math.max(1, win);
        return new Match(best, win, avg);
    }

    private int fineTuneStart(final long[] ref, final int refStart,
                              final long[] fp, final int coarseStart,
                              final int windowFrames) {

        final int edge = Math.min(FINE_TUNE_EDGE_FRAMES, windowFrames);
        final int lo = Math.max(0, coarseStart - FINE_TUNE_RADIUS_FRAMES);
        final int hi = Math.min(fp.length - edge, coarseStart + FINE_TUNE_RADIUS_FRAMES);
        int best = coarseStart;
        double bestBits = Double.MAX_VALUE;
        for (int s = lo; s <= hi; s++) {
            if (refStart + edge > ref.length) {
                break;
            }
            final double bits = this.avgBits(ref, refStart, fp, s, edge);
            if (bits < bestBits) {
                bestBits = bits;
                best = s;
            }
        }
        return best;
    }

    private static Map<String, List<String>> groupByParentDirectory(final List<String> aCaminhos) {

        final Map<String, List<String>> grupos = new TreeMap<>();
        for (String caminho : aCaminhos) {
            final Path p = Paths.get(caminho);
            final String dir = p.getParent() != null ? p.getParent().toString() : "";
            grupos.computeIfAbsent(dir, k -> new ArrayList<>()).add(caminho);
        }
        return grupos;
    }

    private Map<String, Fingerprint> generateFingerprints(final List<String> aCaminhos, final Map<String, Long> aDuracoes) {

        final Map<String, Fingerprint> fingerprints = new HashMap<>();
        final boolean usePool = threadPool != null;
        final ExecutorService pool = usePool ? threadPool : java.util.concurrent.Executors.newFixedThreadPool(this.threadCount);
        try {
            final List<Future<?>> futures = new ArrayList<>();
            for (final String caminho : aCaminhos) {
                final int scanSec = this.scanSecondsFor(aDuracoes.getOrDefault(caminho, 0L));
                futures.add(pool.submit(() -> {
                    try {
                        if (fingerprintCache != null) {
                            final long[] cached = fingerprintCache.get(caminho, scanSec, "ending");
                            if (cached != null) {
                                fingerprints.put(caminho, new Fingerprint(cached, Math.max(0L, aDuracoes.getOrDefault(caminho, 0L) - scanSec)));
                                return;
                            }
                        }
                        final Fingerprint fp = runFpgen(caminho, scanSec);
                        fingerprints.put(caminho, fp);
                        if (fingerprintCache != null && fp.frames().length > 0) {
                            fingerprintCache.put(caminho, scanSec, "ending", fp.frames());
                        }
                    } catch (Exception ignored) {
                        fingerprints.put(caminho, new Fingerprint(new long[0], 0L));
                    }
                }));
            }
            for (Future<?> future : futures) {
                future.get();
            }
        } catch (Exception ignored) {
        } finally {
            if (!usePool) {
                pool.shutdownNow();
            }
        }
        return fingerprints;
    }

    private Fingerprint runFpgen(final String aCaminho, final int aScanSeconds) throws Exception {

        final long duracaoTotal = this.videoDurationReader.readDurationSeconds(aCaminho);
        final long tailStart = Math.max(0L, duracaoTotal - aScanSeconds);

        final List<String> args = new ArrayList<>();
        args.add("ffmpeg");
        args.add("-nostdin");
        args.add("-ss");
        args.add(String.valueOf(tailStart));
        args.add("-i");
        args.add(aCaminho);
        args.add("-t");
        args.add(String.valueOf(aScanSeconds));
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
                throw new IOException("ffmpeg timeout");
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
        return new Fingerprint(fp, tailStart);
    }

    private record Match(int frame, int win, double avgBits) {
    }

    private record Ending(int refStart, int windowFrames, double confidence, boolean detectado, double windowSeconds, int count) {
    }

    public record Fingerprint(long[] frames, long tailStartSeconds) {
    }
}
