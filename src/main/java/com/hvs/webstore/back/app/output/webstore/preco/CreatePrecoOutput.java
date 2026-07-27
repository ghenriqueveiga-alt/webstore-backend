package com.hvs.webstore.back.app.output.webstore.preco;

import com.hvs.webstore.back.domain.entity.webstore.preco.Preco;

public record CreatePrecoOutput(Long aId,
                                String aUuid,
                                String aMessage) {

    public static CreatePrecoOutput from(Preco aPreco) {

        return new CreatePrecoOutput(
                aPreco.getId().getValue(),
                aPreco.getUuid().getValue(),
                "The Preco with id: " + aPreco.getUuid().getValue() + " has been successfully created.");
    }
}
