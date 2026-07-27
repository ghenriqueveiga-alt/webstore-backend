package com.hvs.webstore.back.app.command.webstore.tokenverificacao;

import java.time.Instant;

public record PatchTokenVerificacaoCommand(Long aId,
                                           String aUuid,
                                           String aStatusCode,
                                           Long aUsuarioId,
                                           String aToken,
                                           String aTipoCode,
                                           Instant aExpiradoEm,
                                           Instant aUtilizadoEm) {

    public static PatchTokenVerificacaoCommand from(final Long aId,
                                                    final PatchTokenVerificacaoCommand aIn) {

        return new PatchTokenVerificacaoCommand(
                aId,
                null,
                aIn.aStatusCode,
                aIn.aUsuarioId,
                aIn.aToken,
                aIn.aTipoCode,
                aIn.aExpiradoEm,
                aIn.aUtilizadoEm);
    }

    public static PatchTokenVerificacaoCommand from(final String aUuid,
                                                    final PatchTokenVerificacaoCommand aIn) {

        return new PatchTokenVerificacaoCommand(
                null,
                aUuid,
                aIn.aStatusCode,
                aIn.aUsuarioId,
                aIn.aToken,
                aIn.aTipoCode,
                aIn.aExpiradoEm,
                aIn.aUtilizadoEm);
    }
}
