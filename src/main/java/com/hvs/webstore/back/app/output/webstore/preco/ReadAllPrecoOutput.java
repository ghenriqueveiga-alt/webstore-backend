package com.hvs.webstore.back.app.output.webstore.preco;

import com.hvs.webstore.back.domain.entity.webstore.preco.Preco;
import com.hvs.webstore.back.domain.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

public record ReadAllPrecoOutput(int aCurrentPage,
                                 long aPerPage,
                                 long aTotal,
                                 List<ReadPrecoOutput> aPrecos) {

    public static ReadAllPrecoOutput from(Pagination<Preco> aPagination) {

        var list = new ArrayList<ReadPrecoOutput>();

        for (Preco aPreco : aPagination.aContent())
            list.add(ReadPrecoOutput.from(aPreco));

        return new ReadAllPrecoOutput(
                aPagination.aPageNumber(),
                aPagination.aContent().size(),
                aPagination.aTotalElements(),
                list);
    }
}