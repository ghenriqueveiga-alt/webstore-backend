package com.hvs.webstore.back.app.command.webstore.categoria;

public record ReadCategoriaCommand(Long aId,
                                   String aUuid) {

    public static ReadCategoriaCommand from(final Long aId) {

        return new ReadCategoriaCommand(
                aId,
                null
        );
    }

    public static ReadCategoriaCommand from(final String aUuid) {

        return new ReadCategoriaCommand(
                null,
                aUuid
        );
    }
}
