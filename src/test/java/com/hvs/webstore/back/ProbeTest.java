package com.hvs.webstore.back;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ProbeTest {

    public static void main(String[] args) throws Exception {

        final List<String> caminhos = new ArrayList<>();
        if (args.length > 0) {
            for (String l : Files.readAllLines(Paths.get(args[0]), StandardCharsets.UTF_8)) {
                if (!l.isBlank()) {
                    caminhos.add(l);
                }
            }
        }
        System.out.println("carregados=" + caminhos.size());

        final Object detector = Class.forName("com.hvs.webstore.back.infra.media.ChromaprintIntroDetector").getDeclaredConstructor().newInstance();
        final Method gen = detector.getClass().getDeclaredMethod("generateFingerprints", List.class);
        gen.setAccessible(true);
        @SuppressWarnings("unchecked")
        final java.util.Map<String, long[]> fps = (java.util.Map<String, long[]>) gen.invoke(detector, caminhos);
        System.out.println("fps=" + fps.size());

        final String refNome = fps.keySet().stream().min(String::compareTo).get();
        final long[] ref = fps.get(refNome);
        System.out.println("ref=" + refNome + " frames=" + ref.length);

        final String alvoNome = caminhos.get(1);
        final long[] fp = fps.get(alvoNome);
        System.out.println("alvo=" + alvoNome + " frames=" + fp.length);

        final Method findBest = detector.getClass().getDeclaredMethod("findBestWindow",
                long[].class, int.class, long[].class, int.class);
        findBest.setAccessible(true);
        final Method avgBits = detector.getClass().getDeclaredMethod("avgBits",
                long[].class, int.class, long[].class, int.class, int.class);
        avgBits.setAccessible(true);
        final Method findBestOpening = detector.getClass().getDeclaredMethod("findBestOpening",
                long[].class, java.util.Map.class);
        findBestOpening.setAccessible(true);

        final Object opening = findBestOpening.invoke(detector, ref, fps);
        final Class<?> openingClass = opening.getClass();
        final Field refStartF = openingClass.getDeclaredField("refStart");
        final Field winFramesF = openingClass.getDeclaredField("windowFrames");
        final Field confF = openingClass.getDeclaredField("confidence");
        refStartF.setAccessible(true);
        winFramesF.setAccessible(true);
        confF.setAccessible(true);
        final int refStart = refStartF.getInt(opening);
        final int winFrames = winFramesF.getInt(opening);
        final double conf = confF.getDouble(opening);
        System.out.println("opening refStart=" + refStart + " (" + (refStart * 0.387) + "s) winFrames=" + winFrames + " conf=" + conf);

        final Object match = findBest.invoke(detector, ref, refStart, fp, winFrames);
        final Field frameF = match.getClass().getDeclaredField("frame");
        frameF.setAccessible(true);
        final int start = frameF.getInt(match);
        System.out.println("match start=" + start + " (" + (start * 0.387) + "s)");

        System.out.println("perfil avgBits (janela de 60s deslizando a partir de start):");
        for (int off = 0; off < 120; off += 6) {
            final int len = 60 + off;
            final Object ab = avgBits.invoke(detector, ref, refStart, fp, start, len);
            final double bits = ((Number) ab).doubleValue();
            System.out.printf("  comprimento=%.0fs avgBits=%.2f conf=%.3f%n",
                    (start + len) * 0.387, bits, Math.max(0, 1.0 - bits / 32.0));
        }
    }
}
