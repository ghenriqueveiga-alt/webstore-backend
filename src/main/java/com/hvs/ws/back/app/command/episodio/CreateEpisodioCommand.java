package com.hvs.ws.back.app.command.episodio;

public record CreateEpisodioCommand(Long aArquivoId,
                                    String aTitulo,
                                    Long aNumero,
                                    Long aTemporada,
                                    String aCapaUrl,
                                    Long aProgramaId,
                                    Long parte) {

    public static CreateEpisodioCommand from(final Long aArquivoId,
                                             final String aTitulo,
                                             final Long aNumero,
                                             final Long aTemporada,
                                             final String aCapaUrl,
                                             final Long aProgramaId,
                                             final Long parte) {

        return new CreateEpisodioCommand(
                aArquivoId,
                aTitulo,
                aNumero,
                aTemporada,
                aCapaUrl,
                aProgramaId,
                parte);
    }
}