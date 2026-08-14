package com.hvs.webstore.back.app.output.webstore.marca;

import com.hvs.webstore.back.domain.entity.webstore.marca.Marca;
import com.hvs.webstore.back.domain.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

public record ReadAllMarcaOutput(int aCurrentPage,
                                 long aPerPage,
                                 long aTotal,
                                 List<ReadMarcaOutput> aMarcas) {

    public static ReadAllMarcaOutput from(Pagination<Marca> aPagination) {

        final List<ReadMarcaOutput> list = new ArrayList<>();

        for (Marca aMarca : aPagination.aContent())
            list.add(ReadMarcaOutput.from(aMarca));

        return new ReadAllMarcaOutput(
                aPagination.aPageNumber(),
                aPagination.aContent().size(),
                aPagination.aTotalElements(),
                list);
    }
}
