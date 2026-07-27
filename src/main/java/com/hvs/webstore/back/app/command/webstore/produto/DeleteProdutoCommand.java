package com.hvs.webstore.back.app.command.webstore.produto;

public record DeleteProdutoCommand(Long aId,
                                   String aUuid) {

    public static DeleteProdutoCommand from(final Long aId) {

        return new DeleteProdutoCommand(
                aId,
                null);
    }

    public static DeleteProdutoCommand from(final String aUuid) {

        return new DeleteProdutoCommand(
                null,
                aUuid);
    }
}
