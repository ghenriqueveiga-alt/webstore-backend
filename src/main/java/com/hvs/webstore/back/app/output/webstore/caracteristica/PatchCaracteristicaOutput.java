package com.hvs.webstore.back.app.output.webstore.caracteristica;

import com.hvs.webstore.back.domain.entity.webstore.caracteristica.Caracteristica;

public record PatchCaracteristicaOutput(Long aId,
                                        String aUuid,
                                        String aMessage) {

    public static PatchCaracteristicaOutput from(Caracteristica aCaracteristica) {

        return new PatchCaracteristicaOutput(
                aCaracteristica.getId().getValue(),
                aCaracteristica.getUuid().getValue(),
                "The Caracteristica with id: " + aCaracteristica.getUuid().getValue() + " has been successfully patched.");
    }
}
