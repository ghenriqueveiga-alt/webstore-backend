package com.hvs.webstore.back.app.output.webstore.imposto;

import com.hvs.webstore.back.domain.entity.webstore.imposto.Imposto;
import com.hvs.webstore.back.domain.pagination.Pagination;
import java.util.ArrayList;
import java.util.List;

public record ReadAllImpostoOutput(int aCurrentPage,
                                   long aPerPage,
                                   long aTotal,
                                   java.util.List<ReadImpostoOutput> aImpostos) {

    public static ReadAllImpostoOutput from(Pagination<Imposto> aPagination) {

        final List<ReadImpostoOutput> list = new ArrayList<>();

        for (var i : aPagination.aContent())
            list.add(ReadImpostoOutput.from(i));

        return new ReadAllImpostoOutput(
                aPagination.aPageNumber(),
                aPagination.aContent().size(),
                aPagination.aTotalElements(),
                list);
    }
}
