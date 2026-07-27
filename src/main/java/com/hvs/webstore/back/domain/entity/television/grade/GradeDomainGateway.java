package com.hvs.webstore.back.domain.entity.television.grade;

import com.hvs.webstore.back.app.command.television.grade.GradeSearchQuery;
import com.hvs.webstore.back.domain.pagination.Pagination;

import java.util.Optional;

public interface GradeDomainGateway {

    Grade create(Grade aGrade);

    Optional<Grade> read(GradeId aId);

    Optional<Grade> readByUuid(GradeUuid aUuid);

    Pagination<Grade> readAll(GradeSearchQuery aQuery);

    Grade update(Grade aGrade);

    Grade patch(Grade aGrade);

    void delete(Grade aGrade);
}