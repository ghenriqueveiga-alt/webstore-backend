package com.hvs.webstore.back.app.command.television.corte;

public record CreateCorteCommand(Long aArquivoId,
                                 String aTipoDesc,
                                 String aDuracao,
                                 Long aEpisodioId) {

    public static CreateCorteCommand from(final Long aArquivoId,
                                          final String aTipoDesc,
                                          final String duracao,
                                          final Long aEpisodioId) {

        return new CreateCorteCommand(
                aArquivoId,
                aTipoDesc,
                duracao,
                aEpisodioId);
    }
}