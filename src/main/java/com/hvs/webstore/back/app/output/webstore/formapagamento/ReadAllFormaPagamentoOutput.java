package com.hvs.webstore.back.app.output.webstore.formapagamento;

import com.hvs.webstore.back.domain.entity.webstore.formapagamento.FormaPagamento;
import com.hvs.webstore.back.domain.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

public record ReadAllFormaPagamentoOutput(int aCurrentPage,
                                          long aPerPage,
                                          long aTotal,
                                          List<ReadFormaPagamentoOutput> aFormasPagamento) {

    public static ReadAllFormaPagamentoOutput from(Pagination<FormaPagamento> aPagination) {

        final List<ReadFormaPagamentoOutput> list = new ArrayList<>();

        for (FormaPagamento item : aPagination.aContent())
            list.add(ReadFormaPagamentoOutput.from(item));

        return new ReadAllFormaPagamentoOutput(
                aPagination.aPageNumber(),
                aPagination.aTotalElements(),
                aPagination.aTotalPages(),
                list);
    }
}
