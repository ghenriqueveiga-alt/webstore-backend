package com.hvs.webstore.back.app.output.television.genero;

import com.hvs.webstore.back.domain.entity.television.genero.Genero;

public record PatchGeneroOutput(Long aId,
                                String aUuid,
                                String aMessage) {

    public static PatchGeneroOutput from(final Genero aGenero) {

        return new PatchGeneroOutput(
                aGenero.getId().getValue(),
                aGenero.getUuid().getValue(),
                "The Genero with id: " + aGenero.getUuid().getValue() + " has been successfully patched.");
    }
}
