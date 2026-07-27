package com.hvs.webstore.back.app.output.webstore.carrinhofrete;

import com.hvs.webstore.back.domain.entity.webstore.carrinhofrete.CarrinhoFrete;

public record DeleteCarrinhoFreteOutput(Long id,
                                        String uuid,
                                        String message) {

    public static DeleteCarrinhoFreteOutput from(CarrinhoFrete aCarrinhoFrete) {

        return new DeleteCarrinhoFreteOutput(
                aCarrinhoFrete.getId().getValue(),
                aCarrinhoFrete.getUuid().getValue(),
                "O CarrinhoFrete de id: " + aCarrinhoFrete.getUuid().getValue() + " foi deletado com sucesso.");
    }
}
