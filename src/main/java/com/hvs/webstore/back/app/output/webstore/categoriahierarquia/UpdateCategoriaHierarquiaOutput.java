package com.hvs.webstore.back.app.output.webstore.categoriahierarquia;

import com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia.CategoriaHierarquia;

public record UpdateCategoriaHierarquiaOutput(Long aId,
                                              String aUuid,
                                              String aMessage) {

    public static UpdateCategoriaHierarquiaOutput from(CategoriaHierarquia aCategoriaHierarquia) {

        return new UpdateCategoriaHierarquiaOutput(
                aCategoriaHierarquia.getId().getValue(),
                aCategoriaHierarquia.getUuid().getValue(),
                "The CategoriaHierarquia with id: " + aCategoriaHierarquia.getUuid().getValue() + " has been successfully updated.");
    }
}
