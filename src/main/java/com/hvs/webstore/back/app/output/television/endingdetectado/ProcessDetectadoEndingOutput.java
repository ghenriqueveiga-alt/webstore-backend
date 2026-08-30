package com.hvs.webstore.back.app.output.television.endingdetectado;

import java.util.List;

public record ProcessDetectadoEndingOutput(Boolean aProcessado,
                                           List<EndingDetectadoOutput> aEndingsDetectados) {

    public static ProcessDetectadoEndingOutput from(final Boolean aProcessado,
                                                    final List<EndingDetectadoOutput> aEndingsDetectados) {

        return new ProcessDetectadoEndingOutput(aProcessado, aEndingsDetectados);
    }
}
