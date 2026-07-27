package com.hvs.webstore.back.app.output.television.bloco;

import com.hvs.webstore.back.domain.entity.television.bloco.Bloco;

public record PatchBlocoOutput(Long aId,
                               String aUuid,
                               String aMessage) {

    public static PatchBlocoOutput from(final Bloco aBloco) {

        return new PatchBlocoOutput(
                aBloco.getId().getValue(),
                aBloco.getUuid().getValue(),
                "The Block with id: " + aBloco.getUuid().getValue() + " has been successfully patched.");
    }
}
