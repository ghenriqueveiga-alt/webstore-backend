package com.hvs.webstore.back.app.command.webstore.categoriahierarquia;

public record CreateCategoriaHierarquiaCommand(Long aCategoriaId,
                                               Long aCategoriaPaiId,
                                               Integer aNivel) {

    public static CreateCategoriaHierarquiaCommand from(final Long aCategoriaId,
                                                        final Long aCategoriaPaiId,
                                                        final Integer aNivel) {

        return new CreateCategoriaHierarquiaCommand(
                aCategoriaId,
                aCategoriaPaiId,
                aNivel);
    }
}
