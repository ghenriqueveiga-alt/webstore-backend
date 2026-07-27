package com.hvs.webstore.back.app.output.webstore.imposto;

import com.hvs.webstore.back.domain.entity.webstore.imposto.Imposto;

public record CreateImpostoOutput(Long aId,
                                   String aUuid,
                                   String aMessage) {

    public static CreateImpostoOutput from(Imposto aImposto) {

        return new CreateImpostoOutput(
                aImposto.getId().getValue(),
                aImposto.getUuid().getValue(),
                "Imposto created: " + aImposto.getUuid().getValue());
    }
}
