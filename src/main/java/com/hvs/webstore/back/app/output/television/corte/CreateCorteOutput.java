package com.hvs.webstore.back.app.output.television.corte;

import com.hvs.webstore.back.domain.entity.television.corte.Corte;

public record CreateCorteOutput(Long aId,
                                String aUuid,
                                String aMessage) {

    public static CreateCorteOutput from(final Corte aCorte) {

        return new CreateCorteOutput(
                aCorte.getId().getValue(),
                aCorte.getUuid().getValue(),
                "The Cut with id: " + aCorte.getUuid().getValue() + " has been successfully created.");
    }
}
