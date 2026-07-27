package com.hvs.webstore.back.app.command.webstore.categoriahierarquia;

public record ReadCategoriaHierarquiaCommand(Long aId,
                                             String aUuid) {

    public static ReadCategoriaHierarquiaCommand from(final Long aId) {

        return new ReadCategoriaHierarquiaCommand(
                aId,
                null
        );
    }

    public static ReadCategoriaHierarquiaCommand from(final String aUuid) {

        return new ReadCategoriaHierarquiaCommand(
                null,
                aUuid
        );
    }
}
