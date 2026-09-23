package com.hvs.ws.back.app.command.programa;

public record ReadProgramaCommand(Long aId,
                                  String aUuid){

    public static ReadProgramaCommand from(final Long aId) {

        return new ReadProgramaCommand(aId, null);
    }

    public static ReadProgramaCommand from(final String aUuid) {

        return new ReadProgramaCommand(null, aUuid);
    }
}