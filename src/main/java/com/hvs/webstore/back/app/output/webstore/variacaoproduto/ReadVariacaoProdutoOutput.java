package com.hvs.webstore.back.app.output.webstore.variacaoproduto;

import com.hvs.webstore.back.domain.entity.webstore.variacaoproduto.VariacaoProduto;

public record ReadVariacaoProdutoOutput(Long aId,
                                          String aUuid,
                                          String aStatusDesc,
                                          Long aProdutoId,
                                          String aNome,
                                          String aValor,
                                          String aSku,
                                          Integer aEstoque) {

    public static ReadVariacaoProdutoOutput from(VariacaoProduto aVariacaoProduto) {

        return new ReadVariacaoProdutoOutput(
                aVariacaoProduto.getId().getValue(),
                aVariacaoProduto.getUuid().getValue(),
                aVariacaoProduto.getStatusCode().getDesc(),
                aVariacaoProduto.getProduto() != null ? aVariacaoProduto.getProduto().getId().getValue() : null,
                aVariacaoProduto.getNome(),
                aVariacaoProduto.getValor(),
                aVariacaoProduto.getSku(),
                aVariacaoProduto.getEstoque());
    }

    public static ReadVariacaoProdutoOutput fromSimple(VariacaoProduto aVariacaoProduto) {

        return new ReadVariacaoProdutoOutput(
                aVariacaoProduto.getId().getValue(),
                aVariacaoProduto.getUuid().getValue(),
                null,
                null,
                null,
                null,
                null,
                null);
    }
}
