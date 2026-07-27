package com.hvs.webstore.back.app.output.webstore.logauditoria;

import com.hvs.webstore.back.domain.entity.webstore.logauditoria.LogAuditoria;
import com.hvs.webstore.back.domain.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

public record ReadAllLogAuditoriaOutput(int aCurrentPage,
                                        long aPerPage,
                                        long aTotal,
                                        List<ReadLogAuditoriaOutput> aLogs) {

    public static ReadAllLogAuditoriaOutput from(Pagination<LogAuditoria> aPagination) {

        final List<ReadLogAuditoriaOutput> list = new ArrayList<>();

        for (LogAuditoria a : aPagination.aContent())
            list.add(ReadLogAuditoriaOutput.from(a));

        return new ReadAllLogAuditoriaOutput(
                aPagination.aPageNumber(),
                aPagination.aTotalElements(),
                aPagination.aTotalPages(),
                list);
    }

    public static ReadAllLogAuditoriaOutput from(List<LogAuditoria> aList) {

        final List<ReadLogAuditoriaOutput> list = new ArrayList<>();

        for (LogAuditoria a : aList)
            list.add(ReadLogAuditoriaOutput.from(a));

        return new ReadAllLogAuditoriaOutput(
                0,
                list.size(),
                1,
                list);
    }
}
