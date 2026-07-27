package com.hvs.webstore.back.app.output.webstore.preco;

import com.hvs.webstore.back.domain.entity.webstore.preco.Preco;

public record DeletePrecoOutput(Long aId,
                                String aUuid,
                                String aMessage) {

    public static DeletePrecoOutput from(Preco aPreco) {

        return new DeletePrecoOutput(
                aPreco.getId().getValue(),
                aPreco.getUuid().getValue(),
                "The Preco with id: " + aPreco.getUuid().getValue() + " has been successfully deleted.");
    }
}
