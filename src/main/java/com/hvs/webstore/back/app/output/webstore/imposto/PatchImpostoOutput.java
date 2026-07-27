package com.hvs.webstore.back.app.output.webstore.imposto;

import com.hvs.webstore.back.domain.entity.webstore.imposto.Imposto;

public record PatchImpostoOutput(Long aId,
                                  String aUuid,
                                  String aMessage) {

    public static PatchImpostoOutput from(Imposto aImposto) {

        return new PatchImpostoOutput(
                aImposto.getId().getValue(),
                aImposto.getUuid().getValue(),
                "Imposto patched: " + aImposto.getUuid().getValue());
    }
}
