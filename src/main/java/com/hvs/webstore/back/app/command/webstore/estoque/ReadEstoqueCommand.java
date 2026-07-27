package com.hvs.webstore.back.app.command.webstore.estoque;

public record ReadEstoqueCommand(Long aId,
                                 String aUuid) {

    public static ReadEstoqueCommand from(final Long aId) {

        return new ReadEstoqueCommand(
                aId,
                null
        );
    }

    public static ReadEstoqueCommand from(final String aUuid) {

        return new ReadEstoqueCommand(
                null,
                aUuid
        );
    }
}
