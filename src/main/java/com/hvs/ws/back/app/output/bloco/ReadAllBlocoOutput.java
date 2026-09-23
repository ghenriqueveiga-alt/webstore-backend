package com.hvs.ws.back.app.output.bloco;

import com.hvs.ws.back.domain.entity.bloco.Bloco;
import com.hvs.ws.back.domain.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

public record ReadAllBlocoOutput(int aCurrentPage,
                                 long aPerPage,
                                 long aTotal,
                                 List<ReadBlocoOutput> aBlocos) {

    public static ReadAllBlocoOutput from(final Pagination<Bloco> aBlocoPagination) {

        final List<ReadBlocoOutput> list = new ArrayList<>();

        for (Bloco aBloco : aBlocoPagination.aContent()) {
            list.add(ReadBlocoOutput.from(aBloco));
        }

        return new ReadAllBlocoOutput(
                aBlocoPagination.aPageNumber(),
                aBlocoPagination.aContent().size(),
                aBlocoPagination.aTotalElements(),
                list);
    }
}
