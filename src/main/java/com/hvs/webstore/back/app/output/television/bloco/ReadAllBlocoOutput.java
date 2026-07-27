package com.hvs.webstore.back.app.output.television.bloco;

import com.hvs.webstore.back.domain.entity.television.bloco.Bloco;
import com.hvs.webstore.back.domain.pagination.Pagination;

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
                aBlocoPagination.aTotalElements(),
                aBlocoPagination.aTotalPages(),
                list);
    }
}
