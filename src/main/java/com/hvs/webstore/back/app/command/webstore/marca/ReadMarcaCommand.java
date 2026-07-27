package com.hvs.webstore.back.app.command.webstore.marca;

public record ReadMarcaCommand(Long aId,
                               String aUuid) {

    public static ReadMarcaCommand from(final Long aId) {

        return new ReadMarcaCommand(
                aId,
                null
        );
    }

    public static ReadMarcaCommand from(final String aUuid) {

        return new ReadMarcaCommand(
                null,
                aUuid
        );
    }
}
