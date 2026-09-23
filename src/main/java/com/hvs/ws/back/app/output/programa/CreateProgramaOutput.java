package com.hvs.ws.back.app.output.programa;

import com.hvs.ws.back.domain.entity.programa.Programa;

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
