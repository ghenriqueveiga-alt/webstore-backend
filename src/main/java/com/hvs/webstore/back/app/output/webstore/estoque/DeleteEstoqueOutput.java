package com.hvs.webstore.back.app.output.webstore.estoque;

import com.hvs.webstore.back.domain.entity.webstore.estoque.Estoque;

public record DeleteEstoqueOutput(Long aId,
                                  String aUuid,
                                  String aMessage) {

    public static DeleteEstoqueOutput from(Estoque aEstoque) {

        return new DeleteEstoqueOutput(
                aEstoque.getId().getValue(),
                aEstoque.getUuid().getValue(),
                "The Estoque with id: " + aEstoque.getUuid().getValue() + " has been successfully deleted.");
    }
}
