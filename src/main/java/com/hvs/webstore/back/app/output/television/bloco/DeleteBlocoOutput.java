package com.hvs.webstore.back.app.output.television.bloco;

import com.hvs.webstore.back.domain.entity.television.bloco.Bloco;

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
