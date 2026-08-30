package com.hvs.webstore.back.infra.media;

import com.hvs.webstore.back.app.service.EndingDetector;
import com.hvs.webstore.back.app.service.VideoDurationReader;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class VisualFusionEndingDetector implements EndingDetector {

    private static final Logger LOG = Logger.getLogger(VisualFusionEndingDetector.class.getName());

    private static final double SKIP_VISUAL_CONFIDENCE_HIGH = 0.85;
    private static final double SKIP_VISUAL_CONFIDENCE_LOW = 0.40;

    private final EndingDetector delegate;
    private final VisualFusion fusion;
    private final SceneBoundaryVerifier sceneVerifier;

    public VisualFusionEndingDetector() {
        this(new ChromaprintEndingDetector(new FFprobeVideoDurationReader()), new VisualFusion(), null);
    }

    public VisualFusionEndingDetector(final VideoDurationReader aDurationReader, final int aThreads) {
        this(new ChromaprintEndingDetector(aDurationReader, aThreads), new VisualFusion(aDurationReader), null);
    }

    public VisualFusionEndingDetector(final EndingDetector aDelegate, final VisualFusion aFusion) {
        this(aDelegate, aFusion, null);
    }

    public VisualFusionEndingDetector(final EndingDetector aDelegate, final VisualFusion aFusion,
                                      final SceneBoundaryVerifier aSceneVerifier) {
        this.delegate = aDelegate;
        this.fusion = aFusion;
        this.sceneVerifier = aSceneVerifier;
    }

    @Override
    public Map<String, DetectedEnding> detectEnding(final List<String> aCaminhos) {

        final long t0 = System.currentTimeMillis();
        final Map<String, DetectedEnding> base = this.delegate.detectEnding(aCaminhos);

        final List<VisualFusion.VisualCandidato> candidatosParaVisual = new ArrayList<>();
        final Map<String, DetectedEnding> out = new LinkedHashMap<>();
        int skippedHigh = 0;
        int skippedLow = 0;

        for (DetectedEnding d : base.values()) {
            if (d.aDetectado() && d.aConfianca() >= SKIP_VISUAL_CONFIDENCE_HIGH) {
                out.put(d.aCaminho(), d);
                skippedHigh++;
            } else if (!d.aDetectado() || d.aConfianca() < SKIP_VISUAL_CONFIDENCE_LOW) {
                out.put(d.aCaminho(), d);
                skippedLow++;
            } else {
                candidatosParaVisual.add(new VisualFusion.VisualCandidato(
                        d.aCaminho(), d.aInicioSegundos(), d.aDuracaoSegundos(), d.aConfianca(), d.aDetectado()));
            }
        }

        int detected = 0;
        if (!candidatosParaVisual.isEmpty()) {
            final List<VisualFusion.VisualCandidato> refinados = this.fusion.aplicar(candidatosParaVisual);
            for (VisualFusion.VisualCandidato c : refinados) {
                if (c.detectado()) {
                    out.put(c.caminho(), new DetectedEnding(c.caminho(), c.inicio(), c.dur(), c.conf(), true));
                    detected++;
                } else {
                    out.put(c.caminho(), new DetectedEnding(c.caminho(), 0L, 0.0, 0.0, false));
                }
            }
        }

        final long elapsed = System.currentTimeMillis() - t0;
        LOG.info("VisualFusionEndingDetector: " + detected + "/" + candidatosParaVisual.size()
                + " visual-refined, skipped=" + skippedHigh + " high+" + skippedLow + " low in " + elapsed + "ms");
        return out;
    }
}
