package com.hvs.webstore.back.app.output.webstore.categoria;

import com.hvs.webstore.back.domain.entity.webstore.categoria.Categoria;

public record CreateCategoriaOutput(Long aId,
                                    String aUuid,
                                    String aMessage) {

    public static CreateCategoriaOutput from(Categoria aCategoria) {

        return new CreateCategoriaOutput(
                aCategoria.getId().getValue(),
                aCategoria.getUuid().getValue(),
                "The Categoria with id: " + aCategoria.getUuid().getValue() + " has been successfully created.");
    }
}
