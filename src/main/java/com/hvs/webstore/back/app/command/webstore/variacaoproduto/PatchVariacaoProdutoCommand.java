package com.hvs.webstore.back.app.command.webstore.variacaoproduto;

public record PatchVariacaoProdutoCommand(Long aId,
                                          String aUuid,
                                          String aStatusCode,
                                          Long aProdutoId,
                                          String aNome,
                                          String aValor,
                                          String aSku,
                                          Integer aEstoque) {

    public static PatchVariacaoProdutoCommand from(final Long aId,
                                                   final PatchVariacaoProdutoCommand aIn) {

        return new PatchVariacaoProdutoCommand(
                aId,
                null,
                aIn.aStatusCode,
                aIn.aProdutoId,
                aIn.aNome,
                aIn.aValor,
                aIn.aSku,
                aIn.aEstoque);
    }

    public static PatchVariacaoProdutoCommand from(final String aUuid,
                                                   final PatchVariacaoProdutoCommand aIn) {

        return new PatchVariacaoProdutoCommand(
                null,
                aUuid,
                aIn.aStatusCode,
                aIn.aProdutoId,
                aIn.aNome,
                aIn.aValor,
                aIn.aSku,
                aIn.aEstoque);
    }
}
