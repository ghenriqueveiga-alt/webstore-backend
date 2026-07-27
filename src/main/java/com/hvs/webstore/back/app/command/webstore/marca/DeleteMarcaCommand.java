package com.hvs.webstore.back.app.command.webstore.marca;

public record DeleteMarcaCommand(Long aId,
                                 String aUuid) {

    public static DeleteMarcaCommand from(final Long aId) {

        return new DeleteMarcaCommand(
                aId,
                null);
    }

    public static DeleteMarcaCommand from(final String aUuid) {

        return new DeleteMarcaCommand(
                null,
                aUuid);
    }
}
