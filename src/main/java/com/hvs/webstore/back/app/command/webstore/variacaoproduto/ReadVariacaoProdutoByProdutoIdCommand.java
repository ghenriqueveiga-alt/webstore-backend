package com.hvs.webstore.back.app.command.webstore.variacaoproduto;

public record ReadVariacaoProdutoByProdutoIdCommand(Long aProdutoId) {

    public static ReadVariacaoProdutoByProdutoIdCommand from(final Long aProdutoId) {

        return new ReadVariacaoProdutoByProdutoIdCommand(
                aProdutoId
        );
    }
}
