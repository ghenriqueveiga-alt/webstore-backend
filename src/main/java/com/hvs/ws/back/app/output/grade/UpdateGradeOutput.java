package com.hvs.ws.back.app.output.grade;

import com.hvs.ws.back.domain.entity.grade.Grade;

public record UpdateGradeOutput(Long aId,
                                String aUuid,
                                String aMessage) {

    public static UpdateGradeOutput from(final Grade aGrade) {

        return new UpdateGradeOutput(
                aGrade.getId().getValue(),
                aGrade.getUuid().getValue(),
                "The Grade with id: " + aGrade.getUuid().getValue() + " has been successfully updated.");
    }
}
