package com.hvs.webstore.back.app.command.webstore.tokenverificacao;

import java.time.Instant;

public record UpdateTokenVerificacaoCommand(Long aId,
                                            String aUuid,
                                            String aStatusCode,
                                            Long aUsuarioId,
                                            String aToken,
                                            String aTipoCode,
                                            Instant aExpiradoEm,
                                            Instant aUtilizadoEm,
                                            Instant aCriadoEm) {

    public static UpdateTokenVerificacaoCommand from(final Long aId,
                                                     final UpdateTokenVerificacaoCommand aIn) {

        return new UpdateTokenVerificacaoCommand(
                aId,
                null,
                aIn.aStatusCode,
                aIn.aUsuarioId,
                aIn.aToken,
                aIn.aTipoCode,
                aIn.aExpiradoEm,
                aIn.aUtilizadoEm,
                aIn.aCriadoEm
        );
    }

    public static UpdateTokenVerificacaoCommand from(final String aUuid,
                                                     final UpdateTokenVerificacaoCommand aIn) {

        return new UpdateTokenVerificacaoCommand(
                null,
                aUuid,
                aIn.aStatusCode,
                aIn.aUsuarioId,
                aIn.aToken,
                aIn.aTipoCode,
                aIn.aExpiradoEm,
                aIn.aUtilizadoEm,
                aIn.aCriadoEm
        );
    }
}
