package com.hvs.webstore.back.app.output.webstore.carrinhofrete;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.entity.webstore.carrinhofrete.CarrinhoFrete;

import java.util.List;

public record ReadAllCarrinhoFreteOutput(List<ReadCarrinhoFreteOutput> aCarrinhosFretes) {

    public static ReadAllCarrinhoFreteOutput from(Pagination<CarrinhoFrete> aPagination) {

        return new ReadAllCarrinhoFreteOutput(aPagination.aContent().stream().map(ReadCarrinhoFreteOutput::from).toList());
    }

    public static ReadAllCarrinhoFreteOutput from(List<CarrinhoFrete> aCarrinhoFrete) {

        return new ReadAllCarrinhoFreteOutput(aCarrinhoFrete.stream().map(ReadCarrinhoFreteOutput::from).toList());
    }
}
