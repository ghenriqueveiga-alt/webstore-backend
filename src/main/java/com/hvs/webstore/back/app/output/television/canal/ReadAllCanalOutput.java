package com.hvs.webstore.back.app.output.television.canal;

import com.hvs.webstore.back.domain.entity.television.canal.Canal;
import com.hvs.webstore.back.domain.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

public record ReadAllCanalOutput(int aCurrentPage,
                                 long aPerPage,
                                 long aTotal,
                                 List<ReadCanalOutput> aCanais) {

    public static ReadAllCanalOutput from(final Pagination<Canal> aCanalPagination) {

        final List<ReadCanalOutput> list = new ArrayList<>();

        for (Canal aCanal : aCanalPagination.aContent()) {
            list.add(ReadCanalOutput.from(aCanal));
        }

        return new ReadAllCanalOutput(
                aCanalPagination.aPageNumber(),
                aCanalPagination.aContent().size(),
                aCanalPagination.aTotalElements(),
                list);
    }
}
