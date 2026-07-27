package com.hvs.webstore.back.app.command.webstore.variacaoproduto;

public record DeleteVariacaoProdutoCommand(Long aId,
                                           String aUuid) {

    public static DeleteVariacaoProdutoCommand from(final Long aId) {

        return new DeleteVariacaoProdutoCommand(
                aId,
                null);
    }

    public static DeleteVariacaoProdutoCommand from(final String aUuid) {

        return new DeleteVariacaoProdutoCommand(
                null,
                aUuid);
    }
}
