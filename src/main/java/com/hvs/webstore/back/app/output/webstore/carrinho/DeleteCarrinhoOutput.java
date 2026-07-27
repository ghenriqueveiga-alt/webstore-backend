package com.hvs.webstore.back.app.output.webstore.carrinho;

import com.hvs.webstore.back.domain.entity.webstore.carrinho.Carrinho;

public record DeleteCarrinhoOutput(Long id,
                                   String uuid,
                                   String message) {

    public static DeleteCarrinhoOutput from(Carrinho aCarrinho) {

        return new DeleteCarrinhoOutput(
                aCarrinho.getId().getValue(),
                aCarrinho.getUuid().getValue(),
                "O Carrinho de id: " + aCarrinho.getUuid().getValue() + " foi deletado com sucesso.");
    }
}
