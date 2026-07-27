package com.hvs.webstore.back.app.command.television.episodio;

import java.util.List;

public record UpdateEpisodioCommand(Long aId,
                                    String aUuid,
                                    String aStatusDesc,
                                    Long aArquivoId,
                                    String aTitulo,
                                    Long aNumero,
                                    Long aTemporada,
                                    Long aProgramaId,
                                    List<Long> aCorteIds) {

    public static UpdateEpisodioCommand from(final Long aId,
                                             final UpdateEpisodioCommand aInput) {

        return new UpdateEpisodioCommand(
                aId,
                null,
                aInput.aStatusDesc,
                aInput.aArquivoId,
                aInput.aTitulo,
                aInput.aNumero,
                aInput.aTemporada,
                aInput.aProgramaId,
                aInput.aCorteIds);
    }

    public static UpdateEpisodioCommand from(final String aUuid,
                                             final UpdateEpisodioCommand aInput) {

        return new UpdateEpisodioCommand(
                null,
                aUuid,
                aInput.aStatusDesc,
                aInput.aArquivoId,
                aInput.aTitulo,
                aInput.aNumero,
                aInput.aTemporada,
                aInput.aProgramaId,
                aInput.aCorteIds);
    }
}