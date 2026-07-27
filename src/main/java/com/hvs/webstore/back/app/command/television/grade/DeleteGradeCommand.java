package com.hvs.webstore.back.app.command.television.grade;

public record DeleteGradeCommand(Long aId,
                                 String aUuid) {

    public static DeleteGradeCommand from(final Long aId) {

        return new DeleteGradeCommand(aId, null);
    }

    public static DeleteGradeCommand from(final String aUuid) {

        return new DeleteGradeCommand(null, aUuid);
    }
}