package com.hvs.webstore.back.app.command.television.corte;

import java.time.LocalTime;

public record UpdateCorteCommand(Long aId,
                                 String aUuid,
                                 String aStatusCode,
                                 Long aArquivoId,
                                 String aTipoCode,
                                 String aDuracao,
                                 Long aEpisodioId,
                                 LocalTime aInicio,
                                 LocalTime aFim) {

    public static UpdateCorteCommand from(final Long aId,
                                          final UpdateCorteCommand aInput) {

        return new UpdateCorteCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aArquivoId,
                aInput.aTipoCode,
                aInput.aDuracao,
                aInput.aEpisodioId,
                aInput.aInicio,
                aInput.aFim);
    }
    public static UpdateCorteCommand from(final String aUuid,
                                          final UpdateCorteCommand aInput) {

        return new UpdateCorteCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aArquivoId,
                aInput.aTipoCode,
                aInput.aDuracao,
                aInput.aEpisodioId,
                aInput.aInicio,
                aInput.aFim);
    }
}
