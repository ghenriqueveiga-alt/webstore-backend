package com.hvs.webstore.back.app.output.webstore.variacaoproduto;

import com.hvs.webstore.back.domain.entity.webstore.variacaoproduto.VariacaoProduto;

public record UpdateVariacaoProdutoOutput(Long aId,
                                          String aUuid,
                                          String aMessage) {

    public static UpdateVariacaoProdutoOutput from(VariacaoProduto aVariacaoProduto) {

        return new UpdateVariacaoProdutoOutput(
                aVariacaoProduto.getId().getValue(),
                aVariacaoProduto.getUuid().getValue(),
                "VariacaoProduto updated: " + aVariacaoProduto.getUuid().getValue());
    }
}
