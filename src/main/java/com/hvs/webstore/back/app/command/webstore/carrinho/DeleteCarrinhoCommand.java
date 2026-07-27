package com.hvs.webstore.back.app.command.webstore.carrinho;

public record DeleteCarrinhoCommand(Long aId,
                                    String aUuid) {

    public static DeleteCarrinhoCommand from(final Long aId) {

        return new DeleteCarrinhoCommand(
                aId,
                null);
    }

    public static DeleteCarrinhoCommand from(final String aUuid) {

        return new DeleteCarrinhoCommand(
                null,
                aUuid);
    }
}
