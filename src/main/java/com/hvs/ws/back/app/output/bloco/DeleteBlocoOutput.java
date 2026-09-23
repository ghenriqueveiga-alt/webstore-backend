package com.hvs.ws.back.app.output.bloco;

import com.hvs.ws.back.domain.entity.bloco.Bloco;

public record DeleteBlocoOutput(Long aId,
                                String aUuid,
                                String aMessage) {

    public static DeleteBlocoOutput from(final Bloco aBloco) {

        return new DeleteBlocoOutput(
                aBloco.getId().getValue(),
                aBloco.getUuid().getValue(),
                "The Block with id: " + aBloco.getUuid().getValue() + " has been successfully deleted.");
    }
}
