package com.hvs.webstore.back.app.output.television.genero;

import com.hvs.webstore.back.domain.entity.television.genero.Genero;
import com.hvs.webstore.back.domain.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

public record ReadAllGeneroOutput(int aCurrentPage,
                                  long aPerPage,
                                  long aTotal,
                                  List<ReadGeneroOutput> aGeneros) {

    public static ReadAllGeneroOutput from(final Pagination<Genero> aGeneroPagination) {

        final List<ReadGeneroOutput> list = new ArrayList<>();

        for (Genero aGenero : aGeneroPagination.aContent()) {
            list.add(ReadGeneroOutput.from(aGenero));
        }

        return new ReadAllGeneroOutput(
                aGeneroPagination.aPageNumber(),
                aGeneroPagination.aContent().size(),
                aGeneroPagination.aTotalElements(),
                list);
    }
}
