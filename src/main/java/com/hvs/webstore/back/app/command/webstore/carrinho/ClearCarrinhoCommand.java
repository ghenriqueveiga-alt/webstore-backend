package com.hvs.webstore.back.app.command.webstore.carrinho;

public record ClearCarrinhoCommand(String aCarrinhoUuid) {

    public static ClearCarrinhoCommand from(final String aCarrinhoUuid) {

        return new ClearCarrinhoCommand(aCarrinhoUuid);
    }
}
