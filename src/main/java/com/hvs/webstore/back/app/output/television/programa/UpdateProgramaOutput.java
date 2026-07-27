package com.hvs.webstore.back.app.output.television.programa;

import com.hvs.webstore.back.domain.entity.television.programa.Programa;

public record UpdateProgramaOutput(Long aId,
                                   String aUuid,
                                   String aMessage) {

    public static UpdateProgramaOutput from(final Programa aPrograma) {

        return new UpdateProgramaOutput(
                aPrograma.getId().getValue(),
                aPrograma.getUuid().getValue(),
                "The Program with id: " + aPrograma.getUuid().getValue() + " has been successfully updated.");
    }
}