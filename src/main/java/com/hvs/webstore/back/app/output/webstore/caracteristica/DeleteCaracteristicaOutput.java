package com.hvs.webstore.back.app.output.webstore.caracteristica;

import com.hvs.webstore.back.domain.entity.webstore.caracteristica.Caracteristica;

public record DeleteCaracteristicaOutput(Long aId,
                                         String aUuid,
                                         String aMessage) {

    public static DeleteCaracteristicaOutput from(Caracteristica aCaracteristica) {

        return new DeleteCaracteristicaOutput(
                aCaracteristica.getId().getValue(),
                aCaracteristica.getUuid().getValue(),
                "The Caracteristica with id: " + aCaracteristica.getUuid().getValue() + " has been successfully deleted.");
    }
}
