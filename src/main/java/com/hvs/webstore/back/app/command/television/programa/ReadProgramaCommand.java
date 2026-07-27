package com.hvs.webstore.back.app.command.television.programa;

public record ReadProgramaCommand(Long aId,
                                  String aUuid){

    public static ReadProgramaCommand from(final Long aId) {

        return new ReadProgramaCommand(aId, null);
    }

    public static ReadProgramaCommand from(final String aUuid) {

        return new ReadProgramaCommand(null, aUuid);
    }
}