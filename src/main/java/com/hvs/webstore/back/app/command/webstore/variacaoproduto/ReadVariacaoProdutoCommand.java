package com.hvs.webstore.back.app.command.webstore.variacaoproduto;

public record ReadVariacaoProdutoCommand(Long aId,
                                         String aUuid) {

    public static ReadVariacaoProdutoCommand from(final Long aId) {

        return new ReadVariacaoProdutoCommand(
                aId,
                null
        );
    }

    public static ReadVariacaoProdutoCommand from(final String aUuid) {

        return new ReadVariacaoProdutoCommand(
                null,
                aUuid
        );
    }
}
