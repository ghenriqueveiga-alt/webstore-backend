package com.hvs.webstore.back.app.output.television.grade;

import com.hvs.webstore.back.domain.entity.television.grade.Grade;
import com.hvs.webstore.back.domain.pagination.Pagination;

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
                aGradePagination.aTotalElements(),
                aGradePagination.aTotalPages(),
                list);
    }
}
