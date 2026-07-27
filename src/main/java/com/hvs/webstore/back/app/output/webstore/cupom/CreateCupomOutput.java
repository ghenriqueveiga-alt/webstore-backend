package com.hvs.webstore.back.app.output.webstore.cupom;

import com.hvs.webstore.back.domain.entity.webstore.cupom.Cupom;

public record CreateCupomOutput(Long aId,
                                String aUuid,
                                String aMessage) {

    public static CreateCupomOutput from(Cupom aCupom) {

        return new CreateCupomOutput(
                aCupom.getId().getValue(),
                aCupom.getUuid().getValue(),
                "The Cupom with id: " + aCupom.getUuid().getValue() + " has been successfully created.");
    }
}
