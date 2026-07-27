package com.hvs.webstore.back.app.output.webstore.variacaoproduto;

import com.hvs.webstore.back.domain.entity.webstore.variacaoproduto.VariacaoProduto;

public record CreateVariacaoProdutoOutput(Long aId,
                                          String aUuid,
                                          String aMessage) {

    public static CreateVariacaoProdutoOutput from(VariacaoProduto aVariacaoProduto) {

        return new CreateVariacaoProdutoOutput(
                aVariacaoProduto.getId().getValue(),
                aVariacaoProduto.getUuid().getValue(),
                "VariacaoProduto created: " + aVariacaoProduto.getUuid().getValue());
    }
}
