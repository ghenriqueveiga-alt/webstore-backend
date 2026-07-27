package com.hvs.webstore.back.app.output.webstore.marca;

import com.hvs.webstore.back.domain.entity.webstore.marca.Marca;

public record CreateMarcaOutput(Long aId,
                                String aUuid,
                                String aMessage) {

    public static CreateMarcaOutput from(Marca aMarca) {

        return new CreateMarcaOutput(
                aMarca.getId().getValue(),
                aMarca.getUuid().getValue(),
                "The Marca with id: " + aMarca.getUuid().getValue() + " has been successfully created.");
    }
}
