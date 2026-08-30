package com.hvs.webstore.back;

import com.hvs.webstore.back.infra.media.ChromaprintIntroDetector;
import com.hvs.webstore.back.app.service.IntroDetector;

import java.util.List;
import java.util.Map;

public class DetectorSmokeTest {

    public static void main(String[] args) throws Exception {

        final List<String> caminhos = List.of(
                "F:\\Another - Legendado\\01_The Other.mp4",
                "F:\\Another - Legendado\\02_Rough sketch.mp4",
                "F:\\Another - Legendado\\03_Bone work.mp4",
                "F:\\Another - Legendado\\04_Put flesh.mp4",
                "F:\\Another - Legendado\\05_Build limbs.mp4",
                "F:\\Another - Legendado\\06_Face to face.mp4",
                "F:\\Another - Legendado\\07_Sphere joint.mp4",
                "F:\\Another - Legendado\\08_Hair stand.mp4",
                "F:\\Another - Legendado\\09_Body paint.mp4",
                "F:\\Another - Legendado\\10_Glass Eye.mp4",
                "F:\\Another - Legendado\\11_Makeup.mp4",
                "F:\\Another - Legendado\\12_Stand by oneself.mp4");

        final IntroDetector detector = new ChromaprintIntroDetector();
        final long t0 = System.currentTimeMillis();
        final Map<String, IntroDetector.DetectedIntro> result = detector.detectIntro(caminhos);
        final long t1 = System.currentTimeMillis();
        System.out.println("TOTAL TIME ms=" + (t1 - t0));

        for (String c : caminhos) {
            final IntroDetector.DetectedIntro d = result.get(c);
            System.out.printf("%s -> inicio=%ds dur=%.1fs conf=%.3f det=%s%n",
                    c, d == null ? -1 : d.aInicioSegundos(),
                    d == null ? -1 : d.aDuracaoSegundos(),
                    d == null ? -1 : d.aConfianca(),
                    d == null ? "?" : d.aDetectado());
        }
    }
}