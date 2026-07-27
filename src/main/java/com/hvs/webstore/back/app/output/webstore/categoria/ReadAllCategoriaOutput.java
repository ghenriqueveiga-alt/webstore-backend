package com.hvs.webstore.back.app.output.webstore.categoria;

import com.hvs.webstore.back.domain.entity.webstore.categoria.Categoria;
import com.hvs.webstore.back.domain.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

public record ReadAllCategoriaOutput(int aCurrentPage,
                                     long aPerPage,
                                     long aTotal,
                                     List<ReadCategoriaOutput> aCategorias) {

    public static ReadAllCategoriaOutput from(Pagination<Categoria> aPagination) {

        final List<ReadCategoriaOutput> list = new ArrayList<>();

        for (Categoria aCategoria : aPagination.aContent())
            list.add(ReadCategoriaOutput.from(aCategoria));

        return new ReadAllCategoriaOutput(
                aPagination.aPageNumber(),
                aPagination.aTotalElements(),
                aPagination.aTotalPages(),
                list);
    }
}
