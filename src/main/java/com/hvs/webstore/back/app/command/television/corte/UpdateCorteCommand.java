package com.hvs.webstore.back.app.command.television.corte;

public record UpdateCorteCommand(Long aId,
                                 String aUuid,
                                 String aStatusDesc,
                                 Long aArquivoId,
                                 String aTipoDesc,
                                 String aDuracao,
                                 Long aEpisodioId) {

    public static UpdateCorteCommand from(final Long aId,
                                          final UpdateCorteCommand aInput) {

        return new UpdateCorteCommand(
                aId,
                null,
                aInput.aStatusDesc,
                aInput.aArquivoId,
                aInput.aTipoDesc,
                aInput.aDuracao,
                aInput.aEpisodioId);
    }
    public static UpdateCorteCommand from(final String aUuid,
                                          final UpdateCorteCommand aInput) {

        return new UpdateCorteCommand(
                null,
                aUuid,
                aInput.aStatusDesc,
                aInput.aArquivoId,
                aInput.aTipoDesc,
                aInput.aDuracao,
                aInput.aEpisodioId);
    }
}