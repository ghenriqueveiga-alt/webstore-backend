package com.hvs.webstore.back.app.output.webstore.preco;

import com.hvs.webstore.back.domain.entity.webstore.preco.Preco;

public record ReadPrecoOutput(Long id,
                              String uuid,
                              String status,
                              Long valor,
                              String tipoPagamento,
                              Integer qtdVezesParcelamento,
                              Long valorParcela,
                              Long valorTotalParcelamento) {

    public static ReadPrecoOutput from(Preco aPreco) {

        return new ReadPrecoOutput(
                aPreco.getId().getValue(),
                aPreco.getUuid().getValue(),
                aPreco.getStatusCode().getDesc(),
                aPreco.getValor(),
                aPreco.getTipoPagamento().getDesc(),
                aPreco.getQtdVezesParcelamento(),
                aPreco.getValorParcela(),
                aPreco.getValorTotalParcelamento());
    }

    public static ReadPrecoOutput fromSimple(Preco aPreco) {

        return new ReadPrecoOutput(
                aPreco.getId().getValue(),
                aPreco.getUuid().getValue(),
                null,
                null,
                null,
                null,
                null,
                null);
    }
}