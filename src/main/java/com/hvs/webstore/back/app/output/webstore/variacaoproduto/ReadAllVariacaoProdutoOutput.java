package com.hvs.webstore.back.app.output.webstore.variacaoproduto;

import com.hvs.webstore.back.domain.entity.webstore.variacaoproduto.VariacaoProduto;
import com.hvs.webstore.back.domain.pagination.Pagination;
import java.util.ArrayList;
import java.util.List;

public record ReadAllVariacaoProdutoOutput(int aCurrentPage,
                                           long aPerPage,
                                           long aTotal,
                                           List<ReadVariacaoProdutoOutput> aVariacoes) {

    public static ReadAllVariacaoProdutoOutput from(Pagination<VariacaoProduto> aPagination) {

        var list = new ArrayList<ReadVariacaoProdutoOutput>();

        for (var i : aPagination.aContent())
            list.add(ReadVariacaoProdutoOutput.from(i));

        return new ReadAllVariacaoProdutoOutput(
                aPagination.aPageNumber(),
                aPagination.aContent().size(),
                aPagination.aTotalElements(),
                list);
    }

    public static ReadAllVariacaoProdutoOutput from(List<VariacaoProduto> aVariacaoProduto) {

        var list = aVariacaoProduto.stream().map(ReadVariacaoProdutoOutput::from).toList();

        return new ReadAllVariacaoProdutoOutput(
                0,
                list.size(),
                1,
                list);
    }
}
