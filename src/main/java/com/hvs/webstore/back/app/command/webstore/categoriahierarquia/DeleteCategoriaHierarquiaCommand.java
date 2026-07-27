package com.hvs.webstore.back.app.command.webstore.categoriahierarquia;

public record DeleteCategoriaHierarquiaCommand(Long aId,
                                               String aUuid) {

    public static DeleteCategoriaHierarquiaCommand from(final Long aId) {

        return new DeleteCategoriaHierarquiaCommand(
                aId,
                null);
    }

    public static DeleteCategoriaHierarquiaCommand from(final String aUuid) {

        return new DeleteCategoriaHierarquiaCommand(
                null,
                aUuid);
    }
}
