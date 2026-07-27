package com.hvs.webstore.back.app.output.webstore.produto;

import com.hvs.webstore.back.domain.entity.webstore.produto.Produto;

public record CreateProdutoOutput(Long id,
                                  String uuid,
                                  String message) {

    public static CreateProdutoOutput from(Produto aProduto) {

        return new CreateProdutoOutput(
                aProduto.getId().getValue(),
                aProduto.getUuid().getValue(),
                "O Produto de id: " + aProduto.getUuid().getValue() + " foi criado com sucesso.");
    }
}