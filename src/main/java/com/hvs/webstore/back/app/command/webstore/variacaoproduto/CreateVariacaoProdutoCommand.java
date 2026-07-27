package com.hvs.webstore.back.app.command.webstore.variacaoproduto;

public record CreateVariacaoProdutoCommand(Long aProdutoId,
                                           String aNome,
                                           String aValor,
                                           String aSku,
                                           Integer aEstoque) {

    public static CreateVariacaoProdutoCommand from(final Long aProdutoId,
                                                    final String aNome,
                                                    final String aValor,
                                                    final String aSku,
                                                    final Integer aEstoque) {

        return new CreateVariacaoProdutoCommand(
                aProdutoId,
                aNome,
                aValor,
                aSku,
                aEstoque);
    }
}
