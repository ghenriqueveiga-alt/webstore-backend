package com.hvs.webstore.back;

import com.hvs.webstore.back.infra.media.ChromaprintIntroDetector;
import com.hvs.webstore.back.app.service.IntroDetector;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class ReZeroSmoke {

    public static void main(String[] args) throws Exception {

        final List<String> caminhos = new java.util.ArrayList<>();
        try (var stream = Files.list(Paths.get("F:\\Re Zero\\1ª Temporada"))) {
            stream.filter(p -> p.toString().endsWith(".mp4")).sorted().forEach(p -> caminhos.add(p.toString()));
        }

        final IntroDetector detector = new ChromaprintIntroDetector();
        final long t0 = System.currentTimeMillis();
        final Map<String, IntroDetector.DetectedIntro> result = detector.detectIntro(caminhos);
        final long t1 = System.currentTimeMillis();
        System.out.println("TOTAL TIME ms=" + (t1 - t0));

        for (String c : caminhos) {
            final IntroDetector.DetectedIntro d = result.get(c);
            final String name = Paths.get(c).getFileName().toString();
            System.out.printf("%s -> inicio=%ds dur=%.1fs conf=%.3f det=%s%n",
                    name, d == null ? -1 : d.aInicioSegundos(),
                    d == null ? -1 : d.aDuracaoSegundos(),
                    d == null ? -1 : d.aConfianca(),
                    d == null ? "?" : d.aDetectado());
        }
    }
}