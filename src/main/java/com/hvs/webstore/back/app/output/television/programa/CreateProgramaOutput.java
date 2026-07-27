package com.hvs.webstore.back.app.output.television.programa;

import com.hvs.webstore.back.domain.entity.television.programa.Programa;

public record CreateProgramaOutput(Long aId,
                                   String aUuid,
                                   String aMessage) {

    public static CreateProgramaOutput from(final Programa aPrograma) {

        return new CreateProgramaOutput(
                aPrograma.getId().getValue(),
                aPrograma.getUuid().getValue(),
                "The Program with id: " + aPrograma.getUuid().getValue() + " has been successfully created.");
    }
}
