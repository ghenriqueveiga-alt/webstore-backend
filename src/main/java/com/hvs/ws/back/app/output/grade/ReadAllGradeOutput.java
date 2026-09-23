package com.hvs.ws.back.app.output.grade;

import com.hvs.ws.back.domain.entity.grade.Grade;
import com.hvs.ws.back.domain.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

public record ReadAllGradeOutput(int aCurrentPage,
                                 long aPerPage,
                                 long aTotal,
                                 List<ReadGradeOutput> aGrades) {

    public static ReadAllGradeOutput from(final Pagination<Grade> aGradePagination) {

        final List<ReadGradeOutput> list = new ArrayList<>();

        for (Grade aGrade : aGradePagination.aContent()) {
            list.add(ReadGradeOutput.from(aGrade));
        }

        return new ReadAllGradeOutput(
                aGradePagination.aPageNumber(),
                aGradePagination.aContent().size(),
                aGradePagination.aTotalElements(),
                list);
    }
}
