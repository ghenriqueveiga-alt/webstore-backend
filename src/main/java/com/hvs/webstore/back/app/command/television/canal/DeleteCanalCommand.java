package com.hvs.webstore.back.app.command.television.canal;

public record DeleteCanalCommand(Long aId,
                                 String aUuid) {

    public static DeleteCanalCommand from(final Long aId) {

        return new DeleteCanalCommand(aId, null);
    }

    public static DeleteCanalCommand from(final String aUuid) {

        return new DeleteCanalCommand(null, aUuid);
    }
}
