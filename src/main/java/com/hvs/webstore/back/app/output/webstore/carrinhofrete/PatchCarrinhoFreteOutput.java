package com.hvs.webstore.back.app.output.webstore.carrinhofrete;

import com.hvs.webstore.back.domain.entity.webstore.carrinhofrete.CarrinhoFrete;

public record PatchCarrinhoFreteOutput(Long id,
                                       String uuid,
                                       String message) {

    public static PatchCarrinhoFreteOutput from(CarrinhoFrete aCarrinhoFrete) {

        return new PatchCarrinhoFreteOutput(
                aCarrinhoFrete.getId().getValue(),
                aCarrinhoFrete.getUuid().getValue(),
                "O CarrinhoFrete de id: " + aCarrinhoFrete.getUuid().getValue() + " foi atualizado parcialmente com sucesso.");
    }
}
