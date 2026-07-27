package com.hvs.webstore.back.app.output.webstore.variacaoproduto;

import com.hvs.webstore.back.domain.entity.webstore.variacaoproduto.VariacaoProduto;

public record PatchVariacaoProdutoOutput(Long aId,
                                         String aUuid,
                                         String aMessage) {

    public static PatchVariacaoProdutoOutput from(VariacaoProduto aVariacaoProduto) {

        return new PatchVariacaoProdutoOutput(
                aVariacaoProduto.getId().getValue(),
                aVariacaoProduto.getUuid().getValue(),
                "VariacaoProduto patched: " + aVariacaoProduto.getUuid().getValue());
    }
}
