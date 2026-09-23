package com.hvs.webstore.back;

import com.hvs.webstore.back.infra.media.ChromaprintIntroDetector;
import com.hvs.webstore.back.app.service.IntroDetector;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedetectNaoDetectados {

    public static void main(String[] args) throws Exception {

        final String listFile = args.length > 0 ? args[0]
                : "C:\\Users\\Henrique\\AppData\\Local\\Temp\\opencode\\opcheck\\nao_detectados.txt";

        final List<String> caminhos = new ArrayList<>();
        for (String linha : Files.readAllLines(Paths.get(listFile), StandardCharsets.UTF_8)) {
            final String c = linha.trim();
            if (!c.isEmpty() && Files.exists(Paths.get(c))) {
                caminhos.add(c);
            }
        }
        System.out.println("ARQUIVOS LIDOS=" + caminhos.size());

        final IntroDetector detector = new ChromaprintIntroDetector();
        final long t0 = System.currentTimeMillis();
        final Map<String, IntroDetector.DetectedIntro> result = detector.detectIntro(caminhos);
        final long t1 = System.currentTimeMillis();
        System.out.println("TOTAL TIME ms=" + (t1 - t0));

        final Map<String, List<String>> grupos = new java.util.TreeMap<>();
        for (String c : caminhos) {
            final String dir = Paths.get(c).getParent() != null ? Paths.get(c).getParent().toString() : "";
            grupos.computeIfAbsent(dir, k -> new ArrayList<>()).add(c);
        }

        int detecoes = 0;
        for (Map.Entry<String, List<String>> g : grupos.entrySet()) {
            int d = 0;
            final List<String> novos = new ArrayList<>();
            for (String c : g.getValue()) {
                final IntroDetector.DetectedIntro x = result.get(c);
                if (x != null && x.aDetectado()) {
                    d++;
                    novos.add(Paths.get(c).getFileName().toString()
                            + " inicio=" + x.aInicioSegundos() + "s dur=" + String.format("%.1f", x.aDuracaoSegundos()) + "s conf=" + String.format("%.2f", x.aConfianca()));
                }
            }
            detecoes += d;
            if (d > 0) {
                System.out.println("DIR=" + Paths.get(g.getKey()).getFileName()); // NO-OP placeholder
            }
        }
        System.out.println("TOTAL DETECTADOS=" + detecoes + "/" + caminhos.size());
        System.out.println("=== POR DIRETORIO ===");
        for (Map.Entry<String, List<String>> g : grupos.entrySet()) {
            int d = 0;
            for (String c : g.getValue()) {
                final IntroDetector.DetectedIntro x = result.get(c);
                if (x != null && x.aDetectado()) {
                    d++;
                }
            }
            System.out.println(Paths.get(g.getKey()) + " -> " + d + "/" + g.getValue().size());
            if (d > 0) {
                for (String c : g.getValue()) {
                    final IntroDetector.DetectedIntro x = result.get(c);
                    if (x != null && x.aDetectado()) {
                        System.out.println("    " + Paths.get(c).getFileName() + " inicio=" + x.aInicioSegundos()
                                + "s dur=" + String.format("%.1f", x.aDuracaoSegundos()) + "s conf=" + String.format("%.2f", x.aConfianca()));
                    }
                }
            }
        }
    }
}