package com.hvs.webstore.back.app.command.television.corte;

public record ReadCorteCommand(Long aId, String aUuid){

    public static ReadCorteCommand from(final Long aId) {

        return new ReadCorteCommand(aId, null);
    }

    public static ReadCorteCommand from(final String aUuid) {

        return new ReadCorteCommand(null, aUuid);
    }
}