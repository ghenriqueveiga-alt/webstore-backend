package com.hvs.webstore.back;

import com.hvs.webstore.back.infra.media.ChromaprintIntroDetector;
import com.hvs.webstore.back.app.service.IntroDetector;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InuyashaMultiSmoke {

    public static void main(String[] args) throws Exception {

        final Set<Integer> indices = Set.of(1, 2, 3, 4, 30, 31, 60, 61, 90, 91, 120, 121, 150, 151, 167);
        final List<String> caminhos = new java.util.ArrayList<>();
        try (var stream = java.nio.file.Files.list(java.nio.file.Paths.get("F:\\Inuyasha"))) {
            stream.filter(p -> p.toString().endsWith(".mp4")).sorted().forEach(p -> {
                final String name = p.getFileName().toString();
                final int num = Integer.parseInt(name.replaceAll("^([0-9]+).*", "$1"));
                if (indices.contains(num)) {
                    caminhos.add(p.toString());
                }
            });
        }
        caminhos.sort(String::compareTo);

        final IntroDetector detector = new ChromaprintIntroDetector();
        final long t0 = System.currentTimeMillis();
        final Map<String, IntroDetector.DetectedIntro> result = detector.detectIntro(caminhos);
        final long t1 = System.currentTimeMillis();
        System.out.println("TOTAL TIME ms=" + (t1 - t0));

        for (String c : caminhos) {
            final IntroDetector.DetectedIntro d = result.get(c);
            final String name = java.nio.file.Paths.get(c).getFileName().toString();
            System.out.printf("%s -> inicio=%ds dur=%.1fs conf=%.3f det=%s%n",
                    name, d == null ? -1 : d.aInicioSegundos(),
                    d == null ? -1 : d.aDuracaoSegundos(),
                    d == null ? -1 : d.aConfianca(),
                    d == null ? "?" : d.aDetectado());
        }
    }
}