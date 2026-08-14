package com.hvs.webstore.back.app.command.television.programa;

import java.util.List;

public record PatchProgramaCommand(Long aId,
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

    public static PatchProgramaCommand from(final Long aId,
                                            final PatchProgramaCommand aInput) {

        return new PatchProgramaCommand(
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

    public static PatchProgramaCommand from(final String aUuid,
                                            final PatchProgramaCommand aInput) {

        return new PatchProgramaCommand(
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