package com.hvs.webstore.back.app.output.television.corte;

import com.hvs.webstore.back.domain.entity.television.corte.Corte;

public record UpdateCorteOutput(Long aId,
                                String aUuid,
                                String aMessage) {

    public static UpdateCorteOutput from(final Corte aCorte) {

        return new UpdateCorteOutput(
                aCorte.getId().getValue(),
                aCorte.getUuid().getValue(),
                "The Cut with id: " + aCorte.getUuid().getValue() + " has been successfully updated.");
    }
}
