package com.hvs.webstore.back.app.service;

import java.util.List;
import java.util.Map;

public interface IntroDetector {

    Map<String, DetectedIntro> detectIntro(List<String> aCaminhos);

    record DetectedIntro(String aCaminho, long aInicioSegundos, double aConfianca, boolean aDetectado) {
    }
}
