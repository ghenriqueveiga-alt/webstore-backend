package com.hvs.webstore.back.app.output.webstore.pix;

import com.hvs.webstore.back.domain.entity.webstore.pix.Pix;

public record PatchPixOutput(Long aId,
                             String aUuid,
                             String aMessage) {

    public static PatchPixOutput from(Pix aPix) {

        return new PatchPixOutput(
                aPix.getId().getValue(),
                aPix.getUuid().getValue(),
                "The Pix with id: " + aPix.getUuid().getValue() + " has been successfully patched.");
    }
}
