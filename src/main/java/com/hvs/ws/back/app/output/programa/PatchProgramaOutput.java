package com.hvs.ws.back.app.output.programa;

import com.hvs.ws.back.domain.entity.programa.Programa;

public record PatchProgramaOutput(Long aId,
                                  String aUuid,
                                  String aMessage) {

    public static PatchProgramaOutput from(final Programa aPrograma) {

        return new PatchProgramaOutput(
                aPrograma.getId().getValue(),
                aPrograma.getUuid().getValue(),
                "The Program with id: " + aPrograma.getUuid().getValue() + " has been successfully patched.");
    }
}
