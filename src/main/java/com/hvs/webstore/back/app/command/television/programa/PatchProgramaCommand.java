package com.hvs.webstore.back.app.command.television.programa;

import java.util.List;

public record PatchProgramaCommand(Long aId,
                                   String aUuid,
                                   String aStatusDesc,
                                   String aNome,
                                   Boolean aEmProducao,
                                   String aTipoDesc,
                                   Long aTemporadas,
                                   List<Long> aEpisodioIds,
                                   String aLancamento,
                                   String aEncerramento,
                                   List<Long> aBlocoIds) {

    public static PatchProgramaCommand from(final Long aId,
                                            final PatchProgramaCommand aInput) {

        return new PatchProgramaCommand(
                aId,
                null,
                aInput.aStatusDesc,
                aInput.aNome,
                aInput.aEmProducao,
                aInput.aTipoDesc,
                aInput.aTemporadas,
                aInput.aEpisodioIds,
                aInput.aLancamento,
                aInput.aEncerramento,
                aInput.aBlocoIds);
    }

    public static PatchProgramaCommand from(final String aUuid,
                                            final PatchProgramaCommand aInput) {

        return new PatchProgramaCommand(
                null,
                aUuid,
                aInput.aStatusDesc,
                aInput.aNome,
                aInput.aEmProducao,
                aInput.aTipoDesc,
                aInput.aTemporadas,
                aInput.aEpisodioIds,
                aInput.aLancamento,
                aInput.aEncerramento,
                aInput.aBlocoIds);
    }
}