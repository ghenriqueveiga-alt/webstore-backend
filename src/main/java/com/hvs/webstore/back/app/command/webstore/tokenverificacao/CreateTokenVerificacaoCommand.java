package com.hvs.webstore.back.app.command.webstore.tokenverificacao;

import java.time.Instant;

public record CreateTokenVerificacaoCommand(Long aUsuarioId,
                                            String aToken,
                                            String aTipoCode,
                                            Instant aExpiradoEm) {

    public static CreateTokenVerificacaoCommand from(final Long aUsuarioId,
                                                     final String aToken,
                                                     final String aTipoCode,
                                                     final Instant aExpiradoEm) {

        return new CreateTokenVerificacaoCommand(
                aUsuarioId,
                aToken,
                aTipoCode,
                aExpiradoEm);
    }
}
