package com.hvs.webstore.back.app.output.webstore.pix;

import com.hvs.webstore.back.domain.entity.webstore.pix.Pix;

public record DeletePixOutput(Long aId,
                              String aUuid,
                              String aMessage) {

    public static DeletePixOutput from(Pix aPix) {

        return new DeletePixOutput(
                aPix.getId().getValue(),
                aPix.getUuid().getValue(),
                "The Pix with id: " + aPix.getUuid().getValue() + " has been successfully deleted.");
    }
}
