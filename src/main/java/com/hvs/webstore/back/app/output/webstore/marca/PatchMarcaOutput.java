package com.hvs.webstore.back.app.output.webstore.marca;

import com.hvs.webstore.back.domain.entity.webstore.marca.Marca;

public record PatchMarcaOutput(Long aId,
                               String aUuid,
                               String aMessage) {

    public static PatchMarcaOutput from(Marca aMarca) {

        return new PatchMarcaOutput(
                aMarca.getId().getValue(),
                aMarca.getUuid().getValue(),
                "The Marca with id: " + aMarca.getUuid().getValue() + " has been successfully patched.");
    }
}
