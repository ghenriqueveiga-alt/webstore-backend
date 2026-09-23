package com.hvs.ws.back.app.command.grade;

public record ReadAllGradeCommand(GradeSearchQuery aGradeSearchQuery) {

    public static ReadAllGradeCommand from(final GradeSearchQuery aGradeSearchQuery) {

        return new ReadAllGradeCommand(aGradeSearchQuery);
    }
}