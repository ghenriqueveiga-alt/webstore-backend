package com.hvs.webstore.back.infra.media;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DetectionLogger {

    private static final Logger LOG = Logger.getLogger(DetectionLogger.class.getName());
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final List<DetectionRecord> records = new ArrayList<>();
    private final List<String> failedFiles = new ArrayList<>();
    private long totalProcessingTimeMs = 0;
    private int totalFiles = 0;
    private int detectedCount = 0;
    private double totalConfidence = 0.0;

    public void logDetection(final String aCaminho, final String aTipo,
                             final boolean aDetected, final long aStartSeconds,
                             final double aDuration, final double aConfidence,
                             final long aProcessingTimeMs) {
        records.add(new DetectionRecord(aCaminho, aTipo, aDetected, aStartSeconds,
                aDuration, aConfidence, aProcessingTimeMs));
        totalFiles++;
        totalProcessingTimeMs += aProcessingTimeMs;
        if (aDetected) {
            detectedCount++;
            totalConfidence += aConfidence;
        }
        if (!aDetected) {
            failedFiles.add(aCaminho);
        }
    }

    public void logFailure(final String aCaminho, final String aTipo, final String aReason) {
        failedFiles.add(aCaminho + " [" + aTipo + "]: " + aReason);
    }

    public String summary(final String aTipo) {
        final double avgConf = detectedCount > 0 ? totalConfidence / detectedCount : 0.0;
        final StringBuilder sb = new StringBuilder();
        sb.append("=== Detection Summary (").append(aTipo).append(") ===\n");
        sb.append("Total files:      ").append(totalFiles).append("\n");
        sb.append("Detected:         ").append(detectedCount).append("\n");
        sb.append("Failed:           ").append(failedFiles.size()).append("\n");
        sb.append("Avg confidence:   ").append(String.format("%.3f", avgConf)).append("\n");
        sb.append("Total time (ms):  ").append(totalProcessingTimeMs).append("\n");
        if (totalFiles > 0) {
            sb.append("Avg time/file:   ").append(totalProcessingTimeMs / totalFiles).append(" ms\n");
        }
        if (!failedFiles.isEmpty()) {
            sb.append("Failed files:\n");
            for (int i = 0; i < Math.min(failedFiles.size(), 20); i++) {
                sb.append("  - ").append(failedFiles.get(i)).append("\n");
            }
            if (failedFiles.size() > 20) {
                sb.append("  ... and ").append(failedFiles.size() - 20).append(" more\n");
            }
        }
        return sb.toString();
    }

    public void printSummary(final String aTipo) {
        LOG.info(summary(aTipo));
    }

    public void exportCsv(final Path aPath, final String aTipo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(aPath.toFile()))) {
            pw.println("caminho,tipo,detected,start_seconds,duration,confidence,processing_ms");
            for (DetectionRecord r : records) {
                pw.printf("\"%s\",%s,%b,%d,%.2f,%.3f,%d%n",
                        r.caminho.replace("\"", "\"\""), aTipo, r.detected,
                        r.startSeconds, r.duration, r.confidence, r.processingTimeMs);
            }
            LOG.info("Exported " + records.size() + " records to " + aPath);
        } catch (Exception e) {
            LOG.warning("Failed to export CSV: " + e.getMessage());
        }
    }

    public int totalFiles() {
        return totalFiles;
    }

    public int detectedCount() {
        return detectedCount;
    }

    public List<String> failedFiles() {
        return List.copyOf(failedFiles);
    }

    private record DetectionRecord(String caminho, String tipo, boolean detected,
                                   long startSeconds, double duration, double confidence,
                                   long processingTimeMs) {
    }
}
