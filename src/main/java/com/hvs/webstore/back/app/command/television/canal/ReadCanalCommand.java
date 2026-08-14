package com.hvs.webstore.back.app.command.television.canal;

public record ReadCanalCommand(Long aId,
                               String aUuid) {

    public static ReadCanalCommand from(final Long aId) {

        return new ReadCanalCommand(aId, null);
    }

    public static ReadCanalCommand from(final String aUuid) {

        return new ReadCanalCommand(null, aUuid);
    }
}
