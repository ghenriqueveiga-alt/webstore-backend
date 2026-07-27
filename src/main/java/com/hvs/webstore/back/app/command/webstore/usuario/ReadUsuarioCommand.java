package com.hvs.webstore.back.app.command.webstore.usuario;

public record ReadUsuarioCommand(Long aId,
                                 String aUuid) {

    public static ReadUsuarioCommand from(final Long aId) {

        return new ReadUsuarioCommand(
                aId,
                null
        );
    }

    public static ReadUsuarioCommand from(final String aUuid) {

        return new ReadUsuarioCommand(
                null,
                aUuid
        );
    }
}
