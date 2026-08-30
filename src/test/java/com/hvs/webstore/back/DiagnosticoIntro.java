package com.hvs.webstore.back;

import com.hvs.webstore.back.infra.media.ChromaprintIntroDetector;
import com.hvs.webstore.back.app.service.IntroDetector;

import java.util.Arrays;
import java.util.Map;

public class DiagnosticoIntro {

    public static void main(String[] args) {

        final IntroDetector detector = new ChromaprintIntroDetector();
        final Map<String, IntroDetector.DetectedIntro> res = detector.detectIntro(Arrays.asList(args));
        for (Map.Entry<String, IntroDetector.DetectedIntro> e : res.entrySet()) {
            final IntroDetector.DetectedIntro d = e.getValue();
            System.out.println((d.aDetectado() ? "DETECTADO" : "---") + " " + e.getKey()
                    + " ini=" + d.aInicioSegundos() + " dur=" + Math.round(d.aDuracaoSegundos())
                    + "s conf=" + String.format("%.2f", d.aConfianca()));
        }
    }
}