package com.hvs.webstore.back.app.command.webstore.carrinho;

public record UpdateItemCarrinhoCommand(String aCarrinhoUuid,
                                        String aItemUuid,
                                        Integer aQuantidade) {

    public static UpdateItemCarrinhoCommand from(final String aCarrinhoUuid,
                                                 final String aItemUuid,
                                                 final Integer aQuantidade) {

        return new UpdateItemCarrinhoCommand(
                aCarrinhoUuid,
                aItemUuid,
                aQuantidade
        );
    }
}
