package com.hvs.webstore.back.app.command.webstore.carrinho;

public record RemoveItemCarrinhoCommand(String aCarrinhoUuid,
                                        String aItemUuid) {

    public static RemoveItemCarrinhoCommand from(final String aCarrinhoUuid,
                                                 final String aItemUuid) {

        return new RemoveItemCarrinhoCommand(
                aCarrinhoUuid,
                aItemUuid);
    }
}
