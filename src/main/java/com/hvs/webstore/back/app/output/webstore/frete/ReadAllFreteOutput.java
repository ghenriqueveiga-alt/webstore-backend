package com.hvs.webstore.back.app.output.webstore.frete;

import com.hvs.webstore.back.domain.entity.webstore.frete.Frete;
import com.hvs.webstore.back.domain.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

public record ReadAllFreteOutput(int aCurrentPage,
                                 long aPerPage,
                                 long aTotal,
                                 List<ReadFreteOutput> aFretes) {

    public static ReadAllFreteOutput from(Pagination<Frete> aPagination) {

        final List<ReadFreteOutput> list = new ArrayList<>();

        for (var i : aPagination.aContent())
            list.add(ReadFreteOutput.from(i));

        return new ReadAllFreteOutput(
                aPagination.aPageNumber(),
                aPagination.aContent().size(),
                aPagination.aTotalElements(),
                list);
    }
}
