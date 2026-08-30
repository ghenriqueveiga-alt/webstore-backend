package com.hvs.webstore.back.app.command.television.corte;

import java.time.LocalTime;

public record PatchCorteCommand(Long aId,
                                String aUuid,
                                String aStatusCode,
                                Long aArquivoId,
                                String aTipoCode,
                                String aDuracao,
                                Long aEpisodioId,
                                LocalTime aInicio,
                                LocalTime aFim) {

    public static PatchCorteCommand from(final Long aId,
                                         final PatchCorteCommand aInput) {

        return new PatchCorteCommand(
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
    public static PatchCorteCommand from(final String aUuid,
                                         final PatchCorteCommand aInput) {

        return new PatchCorteCommand(
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
