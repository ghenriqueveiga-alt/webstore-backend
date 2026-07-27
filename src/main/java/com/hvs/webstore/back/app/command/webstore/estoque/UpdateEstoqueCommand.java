package com.hvs.webstore.back.app.command.webstore.estoque;

public record UpdateEstoqueCommand(Long aId,
                                   String aUuid,
                                   String aStatusCode,
                                   Long aProdutoId,
                                   Integer aQuantidade,
                                   Integer aReservado,
                                   Integer aQuantidadeMinima) {

    public static UpdateEstoqueCommand from(final Long aId,
                                            final UpdateEstoqueCommand aInput) {

        return new UpdateEstoqueCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aProdutoId,
                aInput.aQuantidade,
                aInput.aReservado,
                aInput.aQuantidadeMinima
        );
    }

    public static UpdateEstoqueCommand from(final String aUuid,
                                            final UpdateEstoqueCommand aInput) {

        return new UpdateEstoqueCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aProdutoId,
                aInput.aQuantidade,
                aInput.aReservado,
                aInput.aQuantidadeMinima
        );
    }
}
