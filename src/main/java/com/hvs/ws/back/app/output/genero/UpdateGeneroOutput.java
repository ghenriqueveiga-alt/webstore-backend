package com.hvs.ws.back.app.output.genero;

import com.hvs.ws.back.domain.entity.genero.Genero;

public record UpdateGeneroOutput(Long aId,
                                 String aUuid,
                                 String aMessage) {

    public static UpdateGeneroOutput from(final Genero aGenero) {

        return new UpdateGeneroOutput(
                aGenero.getId().getValue(),
                aGenero.getUuid().getValue(),
                "The Genero with id: " + aGenero.getUuid().getValue() + " has been successfully updated.");
    }
}
