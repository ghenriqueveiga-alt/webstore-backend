package com.hvs.webstore.back.app.command.webstore.categoriahierarquia;

public record UpdateCategoriaHierarquiaCommand(Long aId,
                                               String aUuid,
                                               String aStatusCode,
                                               Long aCategoriaId,
                                               Long aCategoriaPaiId,
                                               Integer aNivel) {

    public static UpdateCategoriaHierarquiaCommand from(final Long aId,
                                                        final UpdateCategoriaHierarquiaCommand aInput) {

        return new UpdateCategoriaHierarquiaCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aCategoriaId,
                aInput.aCategoriaPaiId,
                aInput.aNivel
        );
    }

    public static UpdateCategoriaHierarquiaCommand from(final String aUuid,
                                                        final UpdateCategoriaHierarquiaCommand aInput) {

        return new UpdateCategoriaHierarquiaCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aCategoriaId,
                aInput.aCategoriaPaiId,
                aInput.aNivel
        );
    }
}
