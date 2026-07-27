package com.hvs.webstore.back.app.output.webstore.estoque;

import com.hvs.webstore.back.domain.entity.webstore.estoque.Estoque;
import com.hvs.webstore.back.domain.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

public record ReadAllEstoqueOutput(int aCurrentPage,
                                   long aPerPage,
                                   long aTotal,
                                   List<ReadEstoqueOutput> aEstoques) {

    public static ReadAllEstoqueOutput from(Pagination<Estoque> aPagination) {

        final List<ReadEstoqueOutput> list = new ArrayList<>();

        for (Estoque item : aPagination.aContent())
            list.add(ReadEstoqueOutput.from(item));

        return new ReadAllEstoqueOutput(
                aPagination.aPageNumber(),
                aPagination.aTotalElements(),
                aPagination.aTotalPages(),
                list);
    }
}
