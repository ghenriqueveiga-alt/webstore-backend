package com.hvs.webstore.back.app.command.webstore.variacaoproduto;

public record UpdateVariacaoProdutoCommand(Long aId,
                                           String aUuid,
                                           String aStatusCode,
                                           Long aProdutoId,
                                           String aNome,
                                           String aValor,
                                           String aSku,
                                           Integer aEstoque) {

    public static UpdateVariacaoProdutoCommand from(final Long aId,
                                                    final UpdateVariacaoProdutoCommand aIn) {

        return new UpdateVariacaoProdutoCommand(
                aId,
                null,
                aIn.aStatusCode,
                aIn.aProdutoId,
                aIn.aNome,
                aIn.aValor,
                aIn.aSku,
                aIn.aEstoque
        );
    }

    public static UpdateVariacaoProdutoCommand from(final String aUuid,
                                                    final UpdateVariacaoProdutoCommand aIn) {

        return new UpdateVariacaoProdutoCommand(
                null,
                aUuid,
                aIn.aStatusCode,
                aIn.aProdutoId,
                aIn.aNome,
                aIn.aValor,
                aIn.aSku,
                aIn.aEstoque
        );
    }
}
