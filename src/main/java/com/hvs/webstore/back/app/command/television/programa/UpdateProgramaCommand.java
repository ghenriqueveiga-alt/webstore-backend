package com.hvs.webstore.back.app.command.television.programa;

import java.util.List;

public record UpdateProgramaCommand(Long aId,
                                    String aUuid,
                                    String aStatusCode,
                                    String aNome,
                                    Boolean aEmProducao,
                                    String aTipoCode,
                                    Long aTemporadas,
                                    List<Long> aEpisodioIds,
                                    String aLancamento,
                                    String aEncerramento,
                                    List<Long> aBlocoIds,
                                    String aSinopse,
                                    String aClassificacaoEtariaCode,
                                    String aEstudio,
                                    String aDiretor,
                                    String aCapaUrl,
                                    String aTemporadaOriginal,
                                    String aRedeOriginal,
                                    String aTipoExibicaoCode,
                                    String aTituloAlternativo,
                                    String aAudioIdiomas,
                                    String aLegendasDisponiveis,
                                    String aSiteOficial,
                                    List<Long> aGeneroIds) {

    public static UpdateProgramaCommand from(final Long aId,
                                             final UpdateProgramaCommand aInput) {

        return new UpdateProgramaCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aEmProducao,
                aInput.aTipoCode,
                aInput.aTemporadas,
                aInput.aEpisodioIds,
                aInput.aLancamento,
                aInput.aEncerramento,
                aInput.aBlocoIds,
                aInput.aSinopse,
                aInput.aClassificacaoEtariaCode,
                aInput.aEstudio,
                aInput.aDiretor,
                aInput.aCapaUrl,
                aInput.aTemporadaOriginal,
                aInput.aRedeOriginal,
                aInput.aTipoExibicaoCode,
                aInput.aTituloAlternativo,
                aInput.aAudioIdiomas,
                aInput.aLegendasDisponiveis,
                aInput.aSiteOficial,
                aInput.aGeneroIds);
    }

    public static UpdateProgramaCommand from(final String aUuid,
                                             final UpdateProgramaCommand aInput) {

        return new UpdateProgramaCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aEmProducao,
                aInput.aTipoCode,
                aInput.aTemporadas,
                aInput.aEpisodioIds,
                aInput.aLancamento,
                aInput.aEncerramento,
                aInput.aBlocoIds,
                aInput.aSinopse,
                aInput.aClassificacaoEtariaCode,
                aInput.aEstudio,
                aInput.aDiretor,
                aInput.aCapaUrl,
                aInput.aTemporadaOriginal,
                aInput.aRedeOriginal,
                aInput.aTipoExibicaoCode,
                aInput.aTituloAlternativo,
                aInput.aAudioIdiomas,
                aInput.aLegendasDisponiveis,
                aInput.aSiteOficial,
                aInput.aGeneroIds);
    }
}