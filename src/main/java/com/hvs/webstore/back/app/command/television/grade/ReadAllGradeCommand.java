package com.hvs.webstore.back.app.command.television.grade;

public record ReadAllGradeCommand(GradeSearchQuery aGradeSearchQuery) {

    public static ReadAllGradeCommand from(final GradeSearchQuery aGradeSearchQuery) {

        return new ReadAllGradeCommand(aGradeSearchQuery);
    }
}