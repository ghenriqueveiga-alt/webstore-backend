package com.hvs.webstore.back.app.command.television.grade;

import java.util.List;

public record PatchGradeCommand(Long aId,
                                String aUuid,
                                String aStatusDesc,
                                String aNome,
                                String aDescricao,
                                List<Long> aBlocoIds,
                                String aPeriodoInicio,
                                String aPeriodoFim,
                                Boolean aGradeAtiva) {

    public static PatchGradeCommand from(final Long aId,
                                         final PatchGradeCommand aInput) {

        return new PatchGradeCommand(
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

    public static PatchGradeCommand from(final String aUuid,
                                         final PatchGradeCommand aInput) {

        return new PatchGradeCommand(
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