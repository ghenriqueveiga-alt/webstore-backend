package com.hvs.webstore.back.app.command.webstore.categoria;

public record DeleteCategoriaCommand(Long aId,
                                     String aUuid) {

    public static DeleteCategoriaCommand from(final Long aId) {

        return new DeleteCategoriaCommand(
                aId,
                null);
    }

    public static DeleteCategoriaCommand from(final String aUuid) {

        return new DeleteCategoriaCommand(
                null,
                aUuid);
    }
}
