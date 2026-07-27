package com.hvs.webstore.back.app.output.webstore.notafiscal;

import com.hvs.webstore.back.domain.entity.webstore.notafiscal.NotaFiscal;
import com.hvs.webstore.back.domain.pagination.Pagination;
import java.util.ArrayList;
import java.util.List;

public record ReadAllNotaFiscalOutput(int aCurrentPage,
                                      long aPerPage,
                                      long aTotal,
                                      List<ReadNotaFiscalOutput> aNotas) {

    public static ReadAllNotaFiscalOutput from(Pagination<NotaFiscal> aPagination) {

        final List<ReadNotaFiscalOutput> list = new ArrayList<>();

        for (NotaFiscal aNotaFiscal : aPagination.aContent())
            list.add(ReadNotaFiscalOutput.from(aNotaFiscal));

        return new ReadAllNotaFiscalOutput(
                aPagination.aPageNumber(),
                aPagination.aTotalElements(),
                aPagination.aTotalPages(),
                list);
    }

    public static ReadAllNotaFiscalOutput from(List<NotaFiscal> aList) {

        final List<ReadNotaFiscalOutput> list = new ArrayList<>();

        for (NotaFiscal aNotaFiscal : aList)
            list.add(ReadNotaFiscalOutput.from(aNotaFiscal));

        return new ReadAllNotaFiscalOutput(
                0,
                list.size(),
                1,
                list);
    }
}
