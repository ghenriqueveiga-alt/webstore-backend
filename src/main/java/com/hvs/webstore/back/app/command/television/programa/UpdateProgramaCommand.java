package com.hvs.webstore.back.app.command.television.programa;

import java.util.List;

public record UpdateProgramaCommand(Long aId,
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

    public static UpdateProgramaCommand from(final Long aId,
                                             final UpdateProgramaCommand aInput) {

        return new UpdateProgramaCommand(
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

    public static UpdateProgramaCommand from(final String aUuid,
                                             final UpdateProgramaCommand aInput) {

        return new UpdateProgramaCommand(
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