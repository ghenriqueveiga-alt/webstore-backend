package com.hvs.webstore.back.app.command.webstore.estoque;

public record MovimentarEstoqueCommand(String aEstoqueUuid,
                                       Integer aQuantidade,
                                       String aTipo,
                                       String aObservacao) {

    public static MovimentarEstoqueCommand from(final String aEstoqueUuid,
                                                final Integer aQuantidade,
                                                final String aTipo,
                                                final String aObservacao) {

        return new MovimentarEstoqueCommand(
                aEstoqueUuid,
                aQuantidade,
                aTipo,
                aObservacao);
    }
}
