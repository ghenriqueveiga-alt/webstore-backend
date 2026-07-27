package com.hvs.webstore.back.app.command.webstore.caracteristica;

public record ReadCaracteristicaCommand(Long aId,
                                        String aUuid) {

    public static ReadCaracteristicaCommand from(final Long aId) {

        return new ReadCaracteristicaCommand(
                aId,
                null
        );
    }

    public static ReadCaracteristicaCommand from(final String aUuid) {

        return new ReadCaracteristicaCommand(
                null,
                aUuid
        );
    }
}
