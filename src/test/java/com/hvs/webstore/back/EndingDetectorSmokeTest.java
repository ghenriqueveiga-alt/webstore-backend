package com.hvs.webstore.back;

import com.hvs.webstore.back.app.service.EndingDetector;
import com.hvs.webstore.back.app.service.VideoDurationReader;
import com.hvs.webstore.back.infra.media.ChromaprintEndingDetector;
import com.hvs.webstore.back.infra.media.FFprobeVideoDurationReader;

import java.util.List;
import java.util.Map;

public class EndingDetectorSmokeTest {

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

        final VideoDurationReader durationReader = new FFprobeVideoDurationReader();
        final EndingDetector detector = new ChromaprintEndingDetector(durationReader);
        final long t0 = System.currentTimeMillis();
        final Map<String, EndingDetector.DetectedEnding> result = detector.detectEnding(caminhos);
        final long t1 = System.currentTimeMillis();
        System.out.println("TOTAL TIME ms=" + (t1 - t0));

        for (String c : caminhos) {
            final long dur = durationReader.readDurationSeconds(c);
            final EndingDetector.DetectedEnding d = result.get(c);
            System.out.printf("%s (dur=%ds) -> inicio=%ds dur=%.1fs conf=%.3f det=%s%n",
                    c, dur,
                    d == null ? -1 : d.aInicioSegundos(),
                    d == null ? -1 : d.aDuracaoSegundos(),
                    d == null ? -1 : d.aConfianca(),
                    d == null ? "?" : d.aDetectado());
        }
    }
}
