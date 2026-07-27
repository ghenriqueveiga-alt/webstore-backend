package com.hvs.webstore.back.app.command.webstore.estoque;

public record DeleteEstoqueCommand(Long aId,
                                   String aUuid) {

    public static DeleteEstoqueCommand from(final Long aId) {

        return new DeleteEstoqueCommand(
                aId,
                null);
    }

    public static DeleteEstoqueCommand from(final String aUuid) {

        return new DeleteEstoqueCommand(
                null,
                aUuid);
    }
}
