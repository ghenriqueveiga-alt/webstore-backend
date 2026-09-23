package com.hvs.ws.back.app.command.genero;

public record DeleteGeneroCommand(Long aId,
                                  String aUuid) {

    public static DeleteGeneroCommand from(final Long aId) {

        return new DeleteGeneroCommand(aId, null);
    }

    public static DeleteGeneroCommand from(final String aUuid) {

        return new DeleteGeneroCommand(null, aUuid);
    }
}
