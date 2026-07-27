package com.hvs.webstore.back.app.output.webstore.cupom;

import com.hvs.webstore.back.domain.entity.webstore.cupom.Cupom;
import com.hvs.webstore.back.domain.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

public record ReadAllCupomOutput(int aCurrentPage,
                                 long aPerPage,
                                 long aTotal,
                                 List<CupomOutput> aCupons) {

    public static ReadAllCupomOutput from(Pagination<Cupom> aPagination) {

        final List<CupomOutput> list = new ArrayList<>();

        for (Cupom item : aPagination.aContent())
            list.add(CupomOutput.from(item));

        return new ReadAllCupomOutput(
                aPagination.aPageNumber(),
                aPagination.aTotalElements(),
                aPagination.aTotalPages(),
                list);
    }
}
