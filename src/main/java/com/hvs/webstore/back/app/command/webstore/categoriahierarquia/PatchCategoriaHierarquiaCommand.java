package com.hvs.webstore.back.app.command.webstore.categoriahierarquia;

public record PatchCategoriaHierarquiaCommand(Long aId,
                                              String aUuid,
                                              String aStatusCode,
                                              Long aCategoriaId,
                                              Long aCategoriaPaiId,
                                              Integer aNivel) {

    public static PatchCategoriaHierarquiaCommand from(final Long aId,
                                                       final PatchCategoriaHierarquiaCommand aInput) {

        return new PatchCategoriaHierarquiaCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aCategoriaId,
                aInput.aCategoriaPaiId,
                aInput.aNivel);
    }

    public static PatchCategoriaHierarquiaCommand from(final String aUuid,
                                                       final PatchCategoriaHierarquiaCommand aInput) {

        return new PatchCategoriaHierarquiaCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aCategoriaId,
                aInput.aCategoriaPaiId,
                aInput.aNivel);
    }
}
