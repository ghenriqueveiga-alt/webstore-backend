package com.hvs.webstore.back.app.output.television.genero;

import com.hvs.webstore.back.domain.entity.television.genero.Genero;

public record DeleteGeneroOutput(Long aId,
                                 String aUuid,
                                 String aMessage) {

    public static DeleteGeneroOutput from(final Genero aGenero) {

        return new DeleteGeneroOutput(
                aGenero.getId().getValue(),
                aGenero.getUuid().getValue(),
                "The Genero with id: " + aGenero.getUuid().getValue() + " has been successfully deleted.");
    }
}
