package com.hvs.webstore.back.app.command.webstore.estoque;

public record PatchEstoqueCommand(Long aId,
                                  String aUuid,
                                  String aStatusCode,
                                  Long aProdutoId,
                                  Integer aQuantidade,
                                  Integer aReservado,
                                  Integer aQuantidadeMinima) {

    public static PatchEstoqueCommand from(final Long aId,
                                           final PatchEstoqueCommand aInput) {

        return new PatchEstoqueCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aProdutoId,
                aInput.aQuantidade,
                aInput.aReservado,
                aInput.aQuantidadeMinima
        );
    }

    public static PatchEstoqueCommand from(final String aUuid,
                                           final PatchEstoqueCommand aInput) {

        return new PatchEstoqueCommand(
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
