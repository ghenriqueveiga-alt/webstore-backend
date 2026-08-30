package com.hvs.webstore.back.app.output.television.endingdetectado;

import java.util.List;

public record ProcessDetectAllEndingOutput(Boolean aProcessado,
                                           List<EndingDetectadoOutput> aEndingsDetectados) {

    public static ProcessDetectAllEndingOutput from(final Boolean aProcessado,
                                                    final List<EndingDetectadoOutput> aEndingsDetectados) {

        return new ProcessDetectAllEndingOutput(aProcessado, aEndingsDetectados);
    }
}
