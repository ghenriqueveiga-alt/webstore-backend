package com.hvs.webstore.back.app.command.television.episodio;

import java.util.List;

public record CreateEpisodioCommand(Long aArquivoId,
                                    String aTitulo,
                                    Long aNumero,
                                    Long aTemporada,
                                    String aCapaUrl,
                                    Long aProgramaId,
                                    List<Long> aCorteIds) {

    public static CreateEpisodioCommand from(final Long aArquivoId,
                                             final String aTitulo,
                                             final Long aNumero,
                                             final Long aTemporada,
                                             final String aCapaUrl,
                                             final Long aProgramaId,
                                             final List<Long> aCorteIds) {

        return new CreateEpisodioCommand(
                aArquivoId,
                aTitulo,
                aNumero,
                aTemporada,
                aCapaUrl,
                aProgramaId,
                aCorteIds);
    }
}