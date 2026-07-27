package com.hvs.webstore.back.app.output.webstore.preco;

import com.hvs.webstore.back.domain.entity.webstore.preco.Preco;

public record UpdatePrecoOutput(Long aId,
                                String aUuid,
                                String aMessage) {

    public static UpdatePrecoOutput from(Preco aPreco) {

        return new UpdatePrecoOutput(
                aPreco.getId().getValue(),
                aPreco.getUuid().getValue(),
                "The Preco with id: " + aPreco.getUuid().getValue() + " has been successfully updated.");
    }
}
