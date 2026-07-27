package com.hvs.webstore.back.app.command.webstore.tokenverificacao;

public record DeleteTokenVerificacaoCommand(Long aId,
                                            String aUuid) {

    public static DeleteTokenVerificacaoCommand from(final Long aId) {

        return new DeleteTokenVerificacaoCommand(
                aId,
                null);
    }

    public static DeleteTokenVerificacaoCommand from(final String aUuid) {

        return new DeleteTokenVerificacaoCommand(
                null,
                aUuid);
    }
}
