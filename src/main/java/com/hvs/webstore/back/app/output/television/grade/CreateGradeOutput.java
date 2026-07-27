package com.hvs.webstore.back.app.output.television.grade;

import com.hvs.webstore.back.domain.entity.television.grade.Grade;

public record CreateGradeOutput(Long aId,
                                String aUuid,
                                String aMessage) {

    public static CreateGradeOutput from(final Grade aGrade) {

        return new CreateGradeOutput(
                aGrade.getId().getValue(),
                aGrade.getUuid().getValue(),
                "The Grade with id: " + aGrade.getUuid().getValue() + " has been successfully created.");
    }
}
