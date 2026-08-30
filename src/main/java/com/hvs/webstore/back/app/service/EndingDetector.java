package com.hvs.webstore.back.app.service;

import java.util.List;
import java.util.Map;

public interface EndingDetector {

    Map<String, DetectedEnding> detectEnding(List<String> aCaminhos);

    record DetectedEnding(String aCaminho, long aInicioSegundos, double aDuracaoSegundos, double aConfianca, boolean aDetectado) {
    }
}
