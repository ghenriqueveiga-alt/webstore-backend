package com.hvs.webstore.back.app.output.television.grade;

import com.hvs.webstore.back.domain.entity.television.grade.Grade;

public record DeleteGradeOutput(Long aId,
                                String aUuid,
                                String aMessage) {

    public static DeleteGradeOutput from(final Grade aGrade) {

        return new DeleteGradeOutput(
                aGrade.getId().getValue(),
                aGrade.getUuid().getValue(),
                "The Grade with id: " + aGrade.getUuid().getValue() + " has been successfully deleted.");
    }
}
