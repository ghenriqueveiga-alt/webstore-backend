package com.hvs.webstore.back.app.output.webstore.cartao;

import com.hvs.webstore.back.domain.entity.webstore.cartao.Cartao;

import java.util.List;

public record ReadAllCartaoOutput(List<ReadCartaoOutput> aCartoes) {

    public static ReadAllCartaoOutput from(List<Cartao> aCartao) {

        return new ReadAllCartaoOutput(aCartao.stream().map(ReadCartaoOutput::from).toList());
    }
}
