package com.hvs.webstore.back.app.command.webstore.carrinho;

public record AddItemCarrinhoCommand(String aCarrinhoUuid,
                                     Long aProdutoId,
                                     Integer aQuantidade,
                                     Long aPrecoId) {

    public static AddItemCarrinhoCommand from(final String aCarrinhoUuid,
                                              final Long aProdutoId,
                                              final Integer aQuantidade,
                                              final Long aPrecoId) {

        return new AddItemCarrinhoCommand(
                aCarrinhoUuid,
                aProdutoId,
                aQuantidade,
                aPrecoId);
    }
}
