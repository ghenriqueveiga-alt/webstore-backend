package com.hvs.webstore.back.app.output.webstore.produto;

import com.hvs.webstore.back.domain.entity.webstore.produto.Produto;

public record PatchProdutoOutput(Long aId,
                                 String aUuid,
                                 String aMessage) {

    public static PatchProdutoOutput from(Produto aProduto) {

        return new PatchProdutoOutput(
                aProduto.getId().getValue(),
                aProduto.getUuid().getValue(),
                "O Produto de id: " + aProduto.getUuid().getValue() + " foi atualizado com sucesso.");
    }
}
