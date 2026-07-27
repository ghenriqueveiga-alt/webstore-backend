package com.hvs.webstore.back.app.command.webstore.produto;

public record ReadProdutoCommand(Long aId,
                                 String aUuid) {

    public static ReadProdutoCommand from(final Long aId) {

        return new ReadProdutoCommand(
                aId,
                null
        );
    }

    public static ReadProdutoCommand from(final String aUuid) {

        return new ReadProdutoCommand(
                null,
                aUuid
        );
    }
}
