package com.hvs.webstore.back.app.command.television.programa;

import java.util.List;

public record CreateProgramaCommand(String aNome,
                                    Boolean aEmProducao,
                                    String aTipoDesc,
                                    Long aTemporadas,
                                    List<Long> aEpisodioIds,
                                    String aLancamento,
                                    String aEncerramento,
                                    List<Long> aBlocoIds) {

    public static CreateProgramaCommand from(final String aNome,
                                             final Boolean aEmProducao,
                                             final String aTipoCode,
                                             final Long aTemporadas,
                                             final List<Long> aEpisodioIds,
                                             final String aLancamento,
                                             final String aEncerramento,
                                             final List<Long> aBlocoIds) {

        return new CreateProgramaCommand(
                aNome,
                aEmProducao,
                aTipoCode,
                aTemporadas,
                aEpisodioIds,
                aLancamento,
                aEncerramento,
                aBlocoIds
        );
    }
}