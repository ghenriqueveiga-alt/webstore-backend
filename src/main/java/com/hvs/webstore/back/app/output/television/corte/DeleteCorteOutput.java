package com.hvs.webstore.back.app.output.television.corte;

import com.hvs.webstore.back.domain.entity.television.corte.Corte;

public record DeleteCorteOutput(Long aId,
                                String aUuid,
                                String aMessage) {

    public static DeleteCorteOutput from(final Corte aCorte) {

        return new DeleteCorteOutput(
                aCorte.getId().getValue(),
                aCorte.getUuid().getValue(),
                "The Cut with id: " + aCorte.getUuid().getValue() + " has been successfully deleted.");
    }
}
