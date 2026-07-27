package com.hvs.webstore.back.app.output.webstore.estoque;

import com.hvs.webstore.back.domain.entity.webstore.estoque.Estoque;

public record UpdateEstoqueOutput(Long aId,
                                  String aUuid,
                                  String aMessage) {

    public static UpdateEstoqueOutput from(Estoque aEstoque) {

        return new UpdateEstoqueOutput(
                aEstoque.getId().getValue(),
                aEstoque.getUuid().getValue(),
                "The Estoque with id: " + aEstoque.getUuid().getValue() + " has been successfully updated.");
    }
}
