package com.hvs.webstore.back.app.output.television.programa;

import com.hvs.webstore.back.domain.entity.television.programa.Programa;
import com.hvs.webstore.back.domain.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

public record ReadAllProgramaOutput(int aCurrentPage,
                                    long aPerPage,
                                    long aTotal,
                                    List<ReadProgramaOutput> aProgramas) {

    public static ReadAllProgramaOutput from(final Pagination<Programa> aProgramaPagination) {

        final List<ReadProgramaOutput> list = new ArrayList<>();

        for (Programa aPrograma : aProgramaPagination.aContent()) {
            list.add(ReadProgramaOutput.fromMinimal(aPrograma));
        }

        return new ReadAllProgramaOutput(
                aProgramaPagination.aPageNumber(),
                aProgramaPagination.aContent().size(),
                aProgramaPagination.aTotalElements(),
                list);
    }
}
