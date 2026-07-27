package com.hvs.webstore.back.app.output.webstore.avaliacao;

import com.hvs.webstore.back.domain.entity.webstore.avaliacao.Avaliacao;
import com.hvs.webstore.back.domain.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

public record ReadAllAvaliacaoOutput(int aCurrentPage,
                                     long aPerPage,
                                     long aTotal,
                                     List<AvaliacaoOutput> aAvaliacoes) {

    public static ReadAllAvaliacaoOutput from(Pagination<Avaliacao> aPagination) {

        final List<AvaliacaoOutput> list = new ArrayList<>();

        for (Avaliacao aAvaliacao : aPagination.aContent())
            list.add(AvaliacaoOutput.from(aAvaliacao));

        return new ReadAllAvaliacaoOutput(
                aPagination.aPageNumber(),
                aPagination.aTotalElements(),
                aPagination.aTotalPages(),
                list);
    }
}
