package com.hvs.webstore.back.app.command.television.grade;

import java.util.List;

public record CreateGradeCommand(String aNome,
                                 String aDescricao,
                                 List<Long> aBlocoIds,
                                 String aPeriodoInicio,
                                 String aPeriodoFim,
                                 Boolean aGradeAtiva) {

    public static CreateGradeCommand from(final String aNome,
                                          final String aDescricao,
                                          final List<Long> aBlocoIds,
                                          final String aPeriodoInicio,
                                          final String aPeriodoFim,
                                          final Boolean aGradeAtiva) {

        return new CreateGradeCommand(
                aNome,
                aDescricao,
                aBlocoIds,
                aPeriodoInicio,
                aPeriodoFim,
                aGradeAtiva);
    }
}