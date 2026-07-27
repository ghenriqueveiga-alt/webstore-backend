package com.hvs.webstore.back.app.output.television.corte;

import com.hvs.webstore.back.domain.entity.television.corte.Corte;

public record PatchCorteOutput(Long aId,
                               String aUuid,
                               String aMessage) {

    public static PatchCorteOutput from(final Corte aCorte) {

        return new PatchCorteOutput(
                aCorte.getId().getValue(),
                aCorte.getUuid().getValue(),
                "The Cut with id: " + aCorte.getUuid().getValue() + " has been successfully patched.");
    }
}
