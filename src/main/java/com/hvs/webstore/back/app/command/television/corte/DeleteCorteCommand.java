package com.hvs.webstore.back.app.command.television.corte;

public record DeleteCorteCommand(Long aId,
                                 String aUuid) {

    public static DeleteCorteCommand from(final Long aId) {

        return new DeleteCorteCommand(aId, null);
    }

    public static DeleteCorteCommand from(final String aUuid) {

        return new DeleteCorteCommand(null, aUuid);
    }
}