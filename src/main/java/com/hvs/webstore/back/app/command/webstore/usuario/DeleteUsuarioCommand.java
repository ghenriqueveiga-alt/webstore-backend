package com.hvs.webstore.back.app.command.webstore.usuario;

public record DeleteUsuarioCommand(Long aId,
                                   String aUuid) {

    public static DeleteUsuarioCommand from(final Long aId) {

        return new DeleteUsuarioCommand(
                aId,
                null);
    }

    public static DeleteUsuarioCommand from(final String aUuid) {

        return new DeleteUsuarioCommand(
                null,
                aUuid);
    }
}
