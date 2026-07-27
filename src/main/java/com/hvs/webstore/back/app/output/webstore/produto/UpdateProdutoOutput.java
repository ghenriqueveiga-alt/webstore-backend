package com.hvs.webstore.back.app.output.webstore.produto;

import com.hvs.webstore.back.domain.entity.webstore.produto.Produto;

public record UpdateProdutoOutput(Long id,
                                  String uuid,
                                  String message) {

    public static UpdateProdutoOutput from(Produto aProduto) {

        return new UpdateProdutoOutput(
                aProduto.getId().getValue(),
                aProduto.getUuid().getValue(),
                "O Produto de id: " + aProduto.getUuid().getValue() + " foi atualizado com sucesso.");
    }
}
