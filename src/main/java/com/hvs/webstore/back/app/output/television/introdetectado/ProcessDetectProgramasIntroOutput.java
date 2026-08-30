package com.hvs.webstore.back.app.output.television.introdetectado;

import java.util.List;

public record ProcessDetectProgramasIntroOutput(Integer aProgramasProcessados,
                                                Integer aProgramasIgnorados,
                                                Integer aTotalDetectados,
                                                List<String> aDetalhes) {

    public static ProcessDetectProgramasIntroOutput from(final Integer aProgramasProcessados,
                                                         final Integer aProgramasIgnorados,
                                                         final Integer aTotalDetectados,
                                                         final List<String> aDetalhes) {

        return new ProcessDetectProgramasIntroOutput(aProgramasProcessados, aProgramasIgnorados, aTotalDetectados, aDetalhes);
    }
}