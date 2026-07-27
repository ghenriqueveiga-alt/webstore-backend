package com.hvs.webstore.back.app.output.webstore.pix;

import com.hvs.webstore.back.domain.entity.webstore.pix.Pix;
import com.hvs.webstore.back.domain.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

public record ReadAllPixOutput(int aCurrentPage,
                               long aPerPage,
                               long aTotal,
                               List<ReadPixOutput> aPixs) {

    public static ReadAllPixOutput from(Pagination<Pix> aPagination) {

        var list = new ArrayList<ReadPixOutput>();

        for (var aPix : aPagination.aContent())
            list.add(ReadPixOutput.from(aPix));

        return new ReadAllPixOutput(
                aPagination.aPageNumber(),
                aPagination.aTotalElements(),
                aPagination.aTotalPages(),
                list);
    }
}
