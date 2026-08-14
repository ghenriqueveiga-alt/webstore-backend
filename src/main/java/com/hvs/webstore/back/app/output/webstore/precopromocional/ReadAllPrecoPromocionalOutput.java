package com.hvs.webstore.back.app.output.webstore.precopromocional;

import com.hvs.webstore.back.domain.entity.webstore.precopromocional.PrecoPromocional;
import com.hvs.webstore.back.domain.pagination.Pagination;
import java.util.ArrayList;
import java.util.List;

public record ReadAllPrecoPromocionalOutput(int aCurrentPage,
                                            long aPerPage,
                                            long aTotal,
                                            List<ReadPrecoPromocionalOutput> aPrecos) {

    public static ReadAllPrecoPromocionalOutput from(Pagination<PrecoPromocional> aPagination) {

        var list = new ArrayList<ReadPrecoPromocionalOutput>();

        for (var aPrecoPromocional : aPagination.aContent())
            list.add(ReadPrecoPromocionalOutput.from(aPrecoPromocional));

        return new ReadAllPrecoPromocionalOutput(
                aPagination.aPageNumber(),
                aPagination.aContent().size(),
                aPagination.aTotalElements(),
                list);
    }

    public static ReadAllPrecoPromocionalOutput from(List<PrecoPromocional> aPrecoPromocional) {

        var list = aPrecoPromocional.stream().map(ReadPrecoPromocionalOutput::from).toList();

        return new ReadAllPrecoPromocionalOutput(
                0,
                list.size(),
                1,
                list);
    }
}
