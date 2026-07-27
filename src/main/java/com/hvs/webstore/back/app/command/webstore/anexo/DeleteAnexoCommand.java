package com.hvs.webstore.back.app.command.webstore.anexo;

public record DeleteAnexoCommand(Long aId,
                                 String aUuid) {

    public static DeleteAnexoCommand from(final Long aId) {

        return new DeleteAnexoCommand(
                aId,
                null);
    }

    public static DeleteAnexoCommand from(final String aUuid) {

        return new DeleteAnexoCommand(
                null,
                aUuid);
    }
}
