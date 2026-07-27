package com.hvs.webstore.back.app.output.webstore.produto;

import com.hvs.webstore.back.domain.entity.webstore.produto.Produto;

public record DeleteProdutoOutput(Long id,
                                  String uuid,
                                  String message) {

    public static DeleteProdutoOutput from(Produto aProduto) {

        return new DeleteProdutoOutput(
                aProduto.getId().getValue(),
                aProduto.getUuid().getValue(),
                "O Produto de id: " + aProduto.getUuid().getValue() + " foi deletado com sucesso.");
    }
}
