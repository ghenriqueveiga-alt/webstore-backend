package com.hvs.webstore.back.app.output.webstore.carrinhofrete;

import com.hvs.webstore.back.domain.entity.webstore.carrinhofrete.CarrinhoFrete;

public record UpdateCarrinhoFreteOutput(Long id,
                                        String uuid,
                                        String message) {

    public static UpdateCarrinhoFreteOutput from(CarrinhoFrete aCarrinhoFrete) {

        return new UpdateCarrinhoFreteOutput(
                aCarrinhoFrete.getId().getValue(),
                aCarrinhoFrete.getUuid().getValue(),
                "O CarrinhoFrete de id: " + aCarrinhoFrete.getUuid().getValue() + " foi atualizado com sucesso.");
    }
}
