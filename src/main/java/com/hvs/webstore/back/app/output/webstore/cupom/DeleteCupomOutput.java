package com.hvs.webstore.back.app.output.webstore.cupom;

import com.hvs.webstore.back.domain.entity.webstore.cupom.Cupom;

public record DeleteCupomOutput(Long aId,
                                String aUuid,
                                String aMessage) {

    public static DeleteCupomOutput from(Cupom aCupom) {

        return new DeleteCupomOutput(
                aCupom.getId().getValue(),
                aCupom.getUuid().getValue(),
                "The Cupom with id: " + aCupom.getUuid().getValue() + " has been successfully deleted.");
    }
}
