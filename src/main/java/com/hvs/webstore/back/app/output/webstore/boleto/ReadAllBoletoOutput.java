package com.hvs.webstore.back.app.output.webstore.boleto;

import com.hvs.webstore.back.domain.entity.webstore.boleto.Boleto;
import com.hvs.webstore.back.domain.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

public record ReadAllBoletoOutput(int aCurrentPage,
                                  long aPerPage,
                                  long aTotal,
                                  List<ReadBoletoOutput> aBoletos) {

    public static ReadAllBoletoOutput from(Pagination<Boleto> aPagination) {

        final var list = new ArrayList<ReadBoletoOutput>();

        for (var aBoleto : aPagination.aContent())
            list.add(ReadBoletoOutput.from(aBoleto));

        return new ReadAllBoletoOutput(
                aPagination.aPageNumber(),
                aPagination.aContent().size(),
                aPagination.aTotalElements(),
                list);
    }
}
