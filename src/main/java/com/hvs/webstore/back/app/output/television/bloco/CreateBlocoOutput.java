package com.hvs.webstore.back.app.output.television.bloco;

import com.hvs.webstore.back.domain.entity.television.bloco.Bloco;

public record CreateBlocoOutput(Long aId,
                                String aUuid,
                                String aMessage) {

    public static CreateBlocoOutput from(final Bloco aBloco) {

        return new CreateBlocoOutput(
                aBloco.getId().getValue(),
                aBloco.getUuid().getValue(),
                "The Block with id: " + aBloco.getUuid().getValue() + " has been successfully created.");
    }
}
