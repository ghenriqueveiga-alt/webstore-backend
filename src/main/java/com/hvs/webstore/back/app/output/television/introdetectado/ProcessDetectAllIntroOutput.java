package com.hvs.webstore.back.app.output.television.introdetectado;

import java.util.List;

public record ProcessDetectAllIntroOutput(Boolean aProcessado,
                                          List<IntroDetectadoOutput> aIntrosDetectados) {

    public static ProcessDetectAllIntroOutput from(final Boolean aProcessado,
                                                   final List<IntroDetectadoOutput> aIntrosDetectados) {

        return new ProcessDetectAllIntroOutput(aProcessado, aIntrosDetectados);
    }
}