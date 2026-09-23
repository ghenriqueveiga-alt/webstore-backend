package com.hvs.ws.back.app.output.grade;

import com.hvs.ws.back.domain.entity.grade.Grade;

public record PatchGradeOutput(Long aId,
                               String aUuid,
                               String aMessage) {

    public static PatchGradeOutput from(final Grade aGrade) {

        return new PatchGradeOutput(
                aGrade.getId().getValue(),
                aGrade.getUuid().getValue(),
                "The Grade with id: " + aGrade.getUuid().getValue() + " has been successfully patched.");
    }
}
