package com.hvs.webstore.back.app.output.webstore.carrinho;

import com.hvs.webstore.back.domain.entity.webstore.carrinho.Carrinho;

public record CreateCarrinhoOutput(Long id,
                                   String uuid,
                                   String message) {

    public static CreateCarrinhoOutput from(Carrinho aCarrinho) {

        return new CreateCarrinhoOutput(
                aCarrinho.getId().getValue(),
                aCarrinho.getUuid().getValue(),
                "O Carrinho de id: " + aCarrinho.getUuid().getValue() + " foi criado com sucesso.");
    }
}
