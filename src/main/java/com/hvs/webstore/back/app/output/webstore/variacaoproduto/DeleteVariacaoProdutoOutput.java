package com.hvs.webstore.back.app.output.webstore.variacaoproduto;

import com.hvs.webstore.back.domain.entity.webstore.variacaoproduto.VariacaoProduto;

public record DeleteVariacaoProdutoOutput(Long aId,
                                          String aUuid,
                                          String aMessage) {

    public static DeleteVariacaoProdutoOutput from(VariacaoProduto aVariacaoProduto) {

        return new DeleteVariacaoProdutoOutput(
                aVariacaoProduto.getId().getValue(),
                aVariacaoProduto.getUuid().getValue(),
                "VariacaoProduto deleted: " + aVariacaoProduto.getUuid().getValue());
    }
}
