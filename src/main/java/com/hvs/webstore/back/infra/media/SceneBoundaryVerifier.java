package com.hvs.webstore.back.infra.media;

import com.hvs.webstore.back.app.service.VideoCutDetector;

import java.util.List;
import java.util.logging.Logger;

public class SceneBoundaryVerifier {

    private static final Logger LOG = Logger.getLogger(SceneBoundaryVerifier.class.getName());
    private static final double SCENE_PROXIMITY_SECONDS = 3.0;

    private final VideoCutDetector cutDetector;

    public SceneBoundaryVerifier(final VideoCutDetector aCutDetector) {
        this.cutDetector = aCutDetector;
    }

    public boolean verifyIntroBoundary(final String aCaminho, final long aStartSeconds, final long aDurationSeconds) {
        if (aCaminho == null || aCaminho.isBlank()) {
            return true;
        }
        try {
            final List<VideoCutDetector.DetectedScene> scenes = this.cutDetector.detectScenes(aCaminho);
            if (scenes.isEmpty()) {
                return true;
            }
            final long introStart = aStartSeconds;
            final long introEnd = aStartSeconds + aDurationSeconds;
            final boolean hasStartBoundary = scenes.stream()
                    .anyMatch(s -> Math.abs(s.aTimestamp() - introStart) <= SCENE_PROXIMITY_SECONDS);
            final boolean hasEndBoundary = scenes.stream()
                    .anyMatch(s -> Math.abs(s.aTimestamp() - introEnd) <= SCENE_PROXIMITY_SECONDS);
            if (!hasStartBoundary && !hasEndBoundary) {
                LOG.fine("Scene verification: no boundary found near intro " + aCaminho
                        + " start=" + introStart + " end=" + introEnd);
                return false;
            }
            return true;
        } catch (Exception e) {
            LOG.fine("Scene verification failed for " + aCaminho + ": " + e.getMessage());
            return true;
        }
    }

    public boolean verifyEndingBoundary(final String aCaminho, final long aStartSeconds, final long aDurationSeconds) {
        if (aCaminho == null || aCaminho.isBlank()) {
            return true;
        }
        try {
            final List<VideoCutDetector.DetectedScene> scenes = this.cutDetector.detectScenes(aCaminho);
            if (scenes.isEmpty()) {
                return true;
            }
            final long endingStart = aStartSeconds;
            final long endingEnd = aStartSeconds + aDurationSeconds;
            final boolean hasStartBoundary = scenes.stream()
                    .anyMatch(s -> Math.abs(s.aTimestamp() - endingStart) <= SCENE_PROXIMITY_SECONDS);
            final boolean hasEndBoundary = scenes.stream()
                    .anyMatch(s -> Math.abs(s.aTimestamp() - endingEnd) <= SCENE_PROXIMITY_SECONDS);
            if (!hasStartBoundary && !hasEndBoundary) {
                LOG.fine("Scene verification: no boundary found near ending " + aCaminho
                        + " start=" + endingStart + " end=" + endingEnd);
                return false;
            }
            return true;
        } catch (Exception e) {
            LOG.fine("Scene verification failed for " + aCaminho + ": " + e.getMessage());
            return true;
        }
    }

    public long detectPreviewStart(final String aCaminho, final long aEpisodeDuration) {
        if (aCaminho == null || aCaminho.isBlank() || aEpisodeDuration <= 0) {
            return -1L;
        }
        try {
            final List<VideoCutDetector.DetectedScene> scenes = this.cutDetector.detectScenes(aCaminho);
            if (scenes.isEmpty()) {
                return -1L;
            }
            final long previewSearchStart = Math.max(0L, aEpisodeDuration - 45L);
            final long previewSearchEnd = aEpisodeDuration - 10L;
            final long[] candidates = scenes.stream()
                    .mapToLong(VideoCutDetector.DetectedScene::aTimestamp)
                    .filter(t -> t >= previewSearchStart && t <= previewSearchEnd)
                    .toArray();
            if (candidates.length == 0) {
                return -1L;
            }
            long strongestSceneStart = candidates[0];
            double strongestScore = 0.0;
            for (VideoCutDetector.DetectedScene s : scenes) {
                for (long c : candidates) {
                    if (s.aTimestamp() == c && s.aScore() > strongestScore) {
                        strongestScore = s.aScore();
                        strongestSceneStart = c;
                    }
                }
            }
            return strongestSceneStart;
        } catch (Exception e) {
            return -1L;
        }
    }
}
