package com.hvs.webstore.back.app.command.webstore.carrinhofrete;

public record DeleteCarrinhoFreteCommand(Long aId,
                                         String aUuid) {

    public static DeleteCarrinhoFreteCommand from(final Long aId) {

        return new DeleteCarrinhoFreteCommand(
                aId,
                null);
    }

    public static DeleteCarrinhoFreteCommand from(final String aUuid) {

        return new DeleteCarrinhoFreteCommand(
                null,
                aUuid);
    }
}
