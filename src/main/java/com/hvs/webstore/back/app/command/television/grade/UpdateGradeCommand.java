package com.hvs.webstore.back.app.command.television.grade;

import java.util.List;

public record UpdateGradeCommand(Long aId,
                                 String aUuid,
                                 String aStatusDesc,
                                 String aNome,
                                 String aDescricao,
                                 List<Long> aBlocoIds,
                                 String aPeriodoInicio,
                                 String aPeriodoFim,
                                 Boolean aGradeAtiva) {

    public static UpdateGradeCommand from(final Long aId,
                                          final UpdateGradeCommand aInput) {

        return new UpdateGradeCommand(
                aId,
                null,
                aInput.aStatusDesc,
                aInput.aNome,
                aInput.aDescricao,
                aInput.aBlocoIds,
                aInput.aPeriodoInicio,
                aInput.aPeriodoFim,
                aInput.aGradeAtiva);
    }

    public static UpdateGradeCommand from(final String aUuid,
                                          final UpdateGradeCommand aInput) {

        return new UpdateGradeCommand(
                null,
                aUuid,
                aInput.aStatusDesc,
                aInput.aNome,
                aInput.aDescricao,
                aInput.aBlocoIds,
                aInput.aPeriodoInicio,
                aInput.aPeriodoFim,
                aInput.aGradeAtiva);
    }
}