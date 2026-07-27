package com.hvs.webstore.back.app.output.webstore.preco;

import com.hvs.webstore.back.domain.entity.webstore.preco.Preco;

public record PatchPrecoOutput(Long aId,
                               String aUuid,
                               String aMessage) {

    public static PatchPrecoOutput from(Preco aPreco) {

        return new PatchPrecoOutput(
                aPreco.getId().getValue(),
                aPreco.getUuid().getValue(),
                "The Preco with id: " + aPreco.getUuid().getValue() + " has been successfully patched.");
    }
}