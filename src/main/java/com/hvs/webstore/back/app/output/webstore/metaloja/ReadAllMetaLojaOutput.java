package com.hvs.webstore.back.app.output.webstore.metaloja;

import com.hvs.webstore.back.domain.entity.webstore.metaloja.MetaLoja;
import com.hvs.webstore.back.domain.pagination.Pagination;
import java.util.ArrayList;
import java.util.List;

public record ReadAllMetaLojaOutput(int aCurrentPage,
                                    long aPerPage,
                                    long aTotal,
                                    List<ReadMetaLojaOutput> aMetas) {

    public static ReadAllMetaLojaOutput from(Pagination<MetaLoja> aPagination) {

        final List<ReadMetaLojaOutput> list = new ArrayList<>();

        for (MetaLoja aMetaLoja : aPagination.aContent())
            list.add(ReadMetaLojaOutput.from(aMetaLoja));

        return new ReadAllMetaLojaOutput(
                aPagination.aPageNumber(),
                aPagination.aTotalElements(),
                aPagination.aTotalPages(),
                list);
    }

    public static ReadAllMetaLojaOutput from(List<MetaLoja> aList) {

        final List<ReadMetaLojaOutput> list = new ArrayList<>();

        for (MetaLoja aMetaLoja : aList)
            list.add(ReadMetaLojaOutput.from(aMetaLoja));

        return new ReadAllMetaLojaOutput(
                0,
                list.size(),
                1,
                list);
    }
}
