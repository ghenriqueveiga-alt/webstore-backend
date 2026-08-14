package com.hvs.webstore.back.app.output.webstore.caracteristica;

import com.hvs.webstore.back.domain.entity.webstore.caracteristica.Caracteristica;
import com.hvs.webstore.back.domain.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

public record ReadAllCaracteristicaOutput(int aCurrentPage,
                                          long aPerPage,
                                          long aTotal,
                                          List<ReadCaracteristicaOutput> aCaracteristicas) {

    public static ReadAllCaracteristicaOutput from(Pagination<Caracteristica> aPagination) {

        final List<ReadCaracteristicaOutput> list = new ArrayList<>();

        for (Caracteristica aCaracteristica : aPagination.aContent())
            list.add(ReadCaracteristicaOutput.from(aCaracteristica));

        return new ReadAllCaracteristicaOutput(
                aPagination.aPageNumber(),
                aPagination.aContent().size(),
                aPagination.aTotalElements(),
                list);
    }
}
