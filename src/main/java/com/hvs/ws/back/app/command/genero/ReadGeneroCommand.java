package com.hvs.ws.back.app.command.genero;

public record ReadGeneroCommand(Long aId,
                                String aUuid) {

    public static ReadGeneroCommand from(final Long aId) {

        return new ReadGeneroCommand(aId, null);
    }

    public static ReadGeneroCommand from(final String aUuid) {

        return new ReadGeneroCommand(null, aUuid);
    }
}
