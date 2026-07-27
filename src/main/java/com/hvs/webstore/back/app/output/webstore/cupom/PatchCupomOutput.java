package com.hvs.webstore.back.app.output.webstore.cupom;

import com.hvs.webstore.back.domain.entity.webstore.cupom.Cupom;

public record PatchCupomOutput(Long aId,
                               String aUuid,
                               String aMessage) {

    public static PatchCupomOutput from(Cupom aCupom) {

        return new PatchCupomOutput(
                aCupom.getId().getValue(),
                aCupom.getUuid().getValue(),
                "The Cupom with id: " + aCupom.getUuid().getValue() + " has been successfully patched.");
    }
}
