package com.hvs.webstore.back.app.output.webstore.estoque;

import com.hvs.webstore.back.domain.entity.webstore.estoque.MovimentoEstoque;

public record MovimentarEstoqueOutput(Long aId,
                                      String aUuid,
                                      String aMessage) {

    public static MovimentarEstoqueOutput from(MovimentoEstoque aMovimentoEstoque) {

        return new MovimentarEstoqueOutput(
                aMovimentoEstoque.getId().getValue(),
                aMovimentoEstoque.getUuid().getValue(),
                "The MovimentoEstoque with id: " + aMovimentoEstoque.getUuid().getValue() + " has been successfully created.");
    }
}
