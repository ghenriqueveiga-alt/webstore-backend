package com.hvs.webstore.back.app.output.webstore.categoriahierarquia;

import com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia.CategoriaHierarquia;

public record DeleteCategoriaHierarquiaOutput(Long aId,
                                              String aUuid,
                                              String aMessage) {

    public static DeleteCategoriaHierarquiaOutput from(CategoriaHierarquia aCategoriaHierarquia) {

        return new DeleteCategoriaHierarquiaOutput(
                aCategoriaHierarquia.getId().getValue(),
                aCategoriaHierarquia.getUuid().getValue(),
                "The CategoriaHierarquia with id: " + aCategoriaHierarquia.getUuid().getValue() + " has been successfully deleted.");
    }
}
