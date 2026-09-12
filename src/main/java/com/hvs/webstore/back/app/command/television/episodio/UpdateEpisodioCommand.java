package com.hvs.webstore.back.app.command.television.episodio;

import java.util.List;

public record UpdateEpisodioCommand(Long aId,
                                    String aUuid,
                                    String aStatusCode,
                                    Long aArquivoId,
                                    String aTitulo,
                                    Long aNumero,
                                    Long aTemporada,
                                    String aCapaUrl,
                                    Long aProgramaId,
                                    Integer aParte,
                                    List<Long> aCorteIds) {

    public static UpdateEpisodioCommand from(final Long aId,
                                             final UpdateEpisodioCommand aInput) {

        return new UpdateEpisodioCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aArquivoId,
                aInput.aTitulo,
                aInput.aNumero,
                aInput.aTemporada,
                aInput.aCapaUrl,
                aInput.aProgramaId,
                aInput.aParte,
                aInput.aCorteIds);
    }

    public static UpdateEpisodioCommand from(final String aUuid,
                                             final UpdateEpisodioCommand aInput) {

        return new UpdateEpisodioCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aArquivoId,
                aInput.aTitulo,
                aInput.aNumero,
                aInput.aTemporada,
                aInput.aCapaUrl,
                aInput.aProgramaId,
                aInput.aParte,
                aInput.aCorteIds);
    }
}