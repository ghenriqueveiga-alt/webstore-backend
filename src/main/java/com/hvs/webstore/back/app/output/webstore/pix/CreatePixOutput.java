package com.hvs.webstore.back.app.output.webstore.pix;

import com.hvs.webstore.back.domain.entity.webstore.pix.Pix;

public record CreatePixOutput(Long aId,
                              String aUuid,
                              String aMessage) {

    public static CreatePixOutput from(Pix aPix) {

        return new CreatePixOutput(
                aPix.getId().getValue(),
                aPix.getUuid().getValue(),
                "The Pix with id: " + aPix.getUuid().getValue() + " has been successfully created.");
    }
}
