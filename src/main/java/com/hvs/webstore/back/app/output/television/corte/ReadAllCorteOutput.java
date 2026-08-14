package com.hvs.webstore.back.app.output.television.corte;

import com.hvs.webstore.back.domain.entity.television.corte.Corte;
import com.hvs.webstore.back.domain.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

public record ReadAllCorteOutput(int aCurrentPage,
                                 long aPerPage,
                                 long aTotal,
                                 List<ReadCorteOutput> aCortes) {

    public static ReadAllCorteOutput from(final Pagination<Corte> aCortePagination) {

        final List<ReadCorteOutput> list = new ArrayList<>();

        for (Corte aCorte : aCortePagination.aContent()) {
            list.add(ReadCorteOutput.from(aCorte));
        }

        return new ReadAllCorteOutput(
                aCortePagination.aPageNumber(),
                aCortePagination.aContent().size(),
                aCortePagination.aTotalElements(),
                list);
    }
}
