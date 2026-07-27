package com.hvs.webstore.back.app.output.webstore.imposto;

import com.hvs.webstore.back.domain.entity.webstore.imposto.Imposto;

public record UpdateImpostoOutput(Long aId,
                                   String aUuid,
                                   String aMessage) {

    public static UpdateImpostoOutput from(Imposto aImposto) {

        return new UpdateImpostoOutput(
                aImposto.getId().getValue(),
                aImposto.getUuid().getValue(),
                "Imposto updated: " + aImposto.getUuid().getValue());
    }
}
