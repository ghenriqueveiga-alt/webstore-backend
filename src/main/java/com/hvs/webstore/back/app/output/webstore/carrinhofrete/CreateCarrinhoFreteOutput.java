package com.hvs.webstore.back.app.output.webstore.carrinhofrete;

import com.hvs.webstore.back.domain.entity.webstore.carrinhofrete.CarrinhoFrete;

public record CreateCarrinhoFreteOutput(Long id,
                                        String uuid,
                                        String message) {

    public static CreateCarrinhoFreteOutput from(CarrinhoFrete aCarrinhoFrete) {

        return new CreateCarrinhoFreteOutput(
                aCarrinhoFrete.getId().getValue(),
                aCarrinhoFrete.getUuid().getValue(),
                "O CarrinhoFrete de id: " + aCarrinhoFrete.getUuid().getValue() + " foi criado com sucesso.");
    }
}
