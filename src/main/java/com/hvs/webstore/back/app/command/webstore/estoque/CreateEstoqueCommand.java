package com.hvs.webstore.back.app.command.webstore.estoque;

public record CreateEstoqueCommand(Long aProdutoId,
                                   Integer aQuantidade,
                                   Integer aQuantidadeMinima) {

    public static CreateEstoqueCommand from(final Long aProdutoId,
                                            final Integer aQuantidade,
                                            final Integer aQuantidadeMinima) {

        return new CreateEstoqueCommand(
                aProdutoId,
                aQuantidade,
                aQuantidadeMinima);
    }
}
