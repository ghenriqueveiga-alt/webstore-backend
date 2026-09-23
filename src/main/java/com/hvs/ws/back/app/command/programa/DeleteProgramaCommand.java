package com.hvs.ws.back.app.command.programa;

public record DeleteProgramaCommand(Long aId,
                                    String aUuid) {

    public static DeleteProgramaCommand from(final Long aId) {

        return new DeleteProgramaCommand(aId, null);
    }

    public static DeleteProgramaCommand from(final String aUuid) {

        return new DeleteProgramaCommand(null, aUuid);
    }
}
