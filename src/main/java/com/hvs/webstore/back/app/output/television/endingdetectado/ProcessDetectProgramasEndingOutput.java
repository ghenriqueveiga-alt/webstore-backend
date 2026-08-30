package com.hvs.webstore.back.app.output.television.endingdetectado;

import java.util.List;

public record ProcessDetectProgramasEndingOutput(Integer aProgramasProcessados,
                                                 Integer aProgramasIgnorados,
                                                 Integer aTotalDetectados,
                                                 List<String> aDetalhes) {

    public static ProcessDetectProgramasEndingOutput from(final Integer aProgramasProcessados,
                                                          final Integer aProgramasIgnorados,
                                                          final Integer aTotalDetectados,
                                                          final List<String> aDetalhes) {

        return new ProcessDetectProgramasEndingOutput(aProgramasProcessados, aProgramasIgnorados, aTotalDetectados, aDetalhes);
    }
}
