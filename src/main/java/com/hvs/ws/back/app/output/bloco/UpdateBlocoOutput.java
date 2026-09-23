package com.hvs.ws.back.app.output.bloco;

import com.hvs.ws.back.domain.entity.bloco.Bloco;

public record UpdateBlocoOutput(Long aId,
                                String aUuid,
                                String aMessage) {

    public static UpdateBlocoOutput from(final Bloco aBloco) {

        return new UpdateBlocoOutput(
                aBloco.getId().getValue(),
                aBloco.getUuid().getValue(),
                "The Block with id: " + aBloco.getUuid().getValue() + " has been successfully updated.");
    }
}
