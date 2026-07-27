package com.hvs.webstore.back.app.command.webstore.tokenverificacao;

public record ReadTokenVerificacaoCommand(Long aId,
                                          String aUuid) {

    public static ReadTokenVerificacaoCommand from(final Long aId) {

        return new ReadTokenVerificacaoCommand(
                aId,
                null
        );
    }

    public static ReadTokenVerificacaoCommand from(final String aUuid) {

        return new ReadTokenVerificacaoCommand(
                null,
                aUuid
        );
    }
}
