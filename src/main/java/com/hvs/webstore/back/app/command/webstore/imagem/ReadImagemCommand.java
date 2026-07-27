package com.hvs.webstore.back.app.command.webstore.imagem;

public record ReadImagemCommand(Long aId,
                                String aUuid) {

    public static ReadImagemCommand from(final Long aId) {

        return new ReadImagemCommand(
                aId,
                null
        );
    }

    public static ReadImagemCommand from(final String aUuid) {

        return new ReadImagemCommand(
                null,
                aUuid
        );
    }
}
