package com.hvs.webstore.back.app.output.webstore.categoriahierarquia;

import com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia.CategoriaHierarquia;

public record PatchCategoriaHierarquiaOutput(Long aId,
                                             String aUuid,
                                             String aMessage) {

    public static PatchCategoriaHierarquiaOutput from(CategoriaHierarquia aCategoriaHierarquia) {

        return new PatchCategoriaHierarquiaOutput(
                aCategoriaHierarquia.getId().getValue(),
                aCategoriaHierarquia.getUuid().getValue(),
                "The CategoriaHierarquia with id: " + aCategoriaHierarquia.getUuid().getValue() + " has been successfully patched.");
    }
}
