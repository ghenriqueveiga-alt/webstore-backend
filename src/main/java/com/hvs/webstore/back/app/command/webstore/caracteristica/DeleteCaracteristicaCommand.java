package com.hvs.webstore.back.app.command.webstore.caracteristica;

public record DeleteCaracteristicaCommand(Long aId,
                                          String aUuid) {

    public static DeleteCaracteristicaCommand from(final Long aId) {

        return new DeleteCaracteristicaCommand(
                aId,
                null);
    }

    public static DeleteCaracteristicaCommand from(final String aUuid) {

        return new DeleteCaracteristicaCommand(
                null,
                aUuid);
    }
}
