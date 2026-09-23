package com.hvs.ws.back.app.output.genero;

import com.hvs.ws.back.domain.entity.genero.Genero;
import com.hvs.ws.back.domain.pagination.Pagination;

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
