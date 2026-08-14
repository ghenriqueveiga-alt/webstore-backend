package com.hvs.webstore.back.app.service;

import java.util.List;

public interface VideoCutDetector {

    List<DetectedScene> detectScenes(String aCaminho);

    List<DetectedCommercial> detectCommercials(String aCaminho);

    record DetectedScene(String aTipo, long aTimestamp, double aScore) {
    }

    record DetectedCommercial(long aInicio, long aFim, double aDuracaoSegundos) {
    }
}