package com.hvs.ws.back.app.command.grade;

public record ReadGradeCommand(Long aId,
                               String aUuid){

    public static ReadGradeCommand from(final Long aId) {

        return new ReadGradeCommand(aId, null);
    }

    public static ReadGradeCommand from(final String aUuid) {

        return new ReadGradeCommand(null, aUuid);
    }
}