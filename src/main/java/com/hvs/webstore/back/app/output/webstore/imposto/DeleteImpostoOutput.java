package com.hvs.webstore.back.app.output.webstore.imposto;

import com.hvs.webstore.back.domain.entity.webstore.imposto.Imposto;

public record DeleteImpostoOutput(Long aId,
                                   String aUuid,
                                   String aMessage) {

    public static DeleteImpostoOutput from(Imposto aImposto) {

        return new DeleteImpostoOutput(
                aImposto.getId().getValue(),
                aImposto.getUuid().getValue(),
                "Imposto deleted: " + aImposto.getUuid().getValue());
    }
}
