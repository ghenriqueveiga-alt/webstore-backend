package com.hvs.webstore.back.app.output.webstore.categoriahierarquia;

import com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia.CategoriaHierarquia;
import com.hvs.webstore.back.domain.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

public record ReadAllCategoriaHierarquiaOutput(int aCurrentPage,
                                               long aPerPage,
                                               long aTotal,
                                               List<ReadCategoriaHierarquiaOutput> aCategoriasHierarquia) {

    public static ReadAllCategoriaHierarquiaOutput from(Pagination<CategoriaHierarquia> aPagination) {

        final List<ReadCategoriaHierarquiaOutput> list = new ArrayList<>();

        for (CategoriaHierarquia item : aPagination.aContent())
            list.add(ReadCategoriaHierarquiaOutput.from(item));

        return new ReadAllCategoriaHierarquiaOutput(
                aPagination.aPageNumber(),
                aPagination.aContent().size(),
                aPagination.aTotalElements(),
                list);
    }

    public static ReadAllCategoriaHierarquiaOutput from(List<CategoriaHierarquia> aCategoriaHierarquia) {

        final List<ReadCategoriaHierarquiaOutput> list = new ArrayList<>();

        for (CategoriaHierarquia item : aCategoriaHierarquia)
            list.add(ReadCategoriaHierarquiaOutput.from(item));

        return new ReadAllCategoriaHierarquiaOutput(
                0,
                list.size(),
                list.size(),
                list);
    }
}
