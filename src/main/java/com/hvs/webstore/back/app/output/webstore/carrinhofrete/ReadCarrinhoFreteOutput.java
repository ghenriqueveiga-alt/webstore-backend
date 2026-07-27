package com.hvs.webstore.back.app.output.webstore.carrinhofrete;

import com.hvs.webstore.back.domain.entity.webstore.carrinhofrete.CarrinhoFrete;

public record ReadCarrinhoFreteOutput(Long id,
                                      String uuid,
                                      String status,
                                      Long carrinhoId,
                                      Long freteId,
                                      Long valor,
                                      Integer prazo,
                                      Long transportadoraId) {

    public static ReadCarrinhoFreteOutput from(CarrinhoFrete aCarrinhoFrete) {

        return new ReadCarrinhoFreteOutput(
                aCarrinhoFrete.getId().getValue(),
                aCarrinhoFrete.getUuid().getValue(),
                aCarrinhoFrete.getStatusCode().getDesc(),
                aCarrinhoFrete.getCarrinho().getId().getValue(),
                aCarrinhoFrete.getFrete().getId().getValue(),
                aCarrinhoFrete.getValor(),
                aCarrinhoFrete.getPrazo(),
                aCarrinhoFrete.getTransportadora() != null ? aCarrinhoFrete.getTransportadora().getId().getValue() : null);
    }

    public static ReadCarrinhoFreteOutput fromSimple(CarrinhoFrete aCarrinhoFrete) {

        return new ReadCarrinhoFreteOutput(
                aCarrinhoFrete.getId().getValue(),
                aCarrinhoFrete.getUuid().getValue(),
                null,
                null,
                null,
                null,
                null,
                null);
    }
}
