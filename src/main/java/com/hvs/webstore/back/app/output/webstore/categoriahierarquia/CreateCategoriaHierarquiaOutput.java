package com.hvs.webstore.back.app.output.webstore.categoriahierarquia;

import com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia.CategoriaHierarquia;

public record CreateCategoriaHierarquiaOutput(Long aId,
                                              String aUuid,
                                              String aMessage) {

    public static CreateCategoriaHierarquiaOutput from(CategoriaHierarquia aCategoriaHierarquia) {

        return new CreateCategoriaHierarquiaOutput(
                aCategoriaHierarquia.getId().getValue(),
                aCategoriaHierarquia.getUuid().getValue(),
                "The CategoriaHierarquia with id: " + aCategoriaHierarquia.getUuid().getValue() + " has been successfully created.");
    }
}
