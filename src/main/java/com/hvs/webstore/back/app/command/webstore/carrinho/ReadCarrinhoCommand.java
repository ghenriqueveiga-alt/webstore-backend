package com.hvs.webstore.back.app.command.webstore.carrinho;

public record ReadCarrinhoCommand(Long aId,
                                  String aUuid) {

    public static ReadCarrinhoCommand from(final Long aId) {

        return new ReadCarrinhoCommand(
                aId,
                null
        );
    }

    public static ReadCarrinhoCommand from(final String aUuid) {

        return new ReadCarrinhoCommand(
                null,
                aUuid
        );
    }
}
