package com.hvs.webstore.back.app.output.television.programa;

import com.hvs.webstore.back.domain.entity.television.programa.Programa;

public record DeleteProgramaOutput(Long aId,
                                   String aUuid,
                                   String aMessage) {

    public static DeleteProgramaOutput from(final Programa aPrograma) {

        return new DeleteProgramaOutput(
                aPrograma.getId().getValue(),
                aPrograma.getUuid().getValue(),
                "The Program with id: " + aPrograma.getUuid().getValue() + " has been successfully deleted.");
    }
}
