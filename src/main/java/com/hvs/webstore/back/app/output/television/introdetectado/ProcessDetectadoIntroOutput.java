package com.hvs.webstore.back.app.output.television.introdetectado;

import java.util.List;

public record ProcessDetectadoIntroOutput(Boolean aProcessado,
                                          List<IntroDetectadoOutput> aIntrosDetectados) {

    public static ProcessDetectadoIntroOutput from(final Boolean aProcessado,
                                                   final List<IntroDetectadoOutput> aIntrosDetectados) {

        return new ProcessDetectadoIntroOutput(aProcessado, aIntrosDetectados);
    }
}