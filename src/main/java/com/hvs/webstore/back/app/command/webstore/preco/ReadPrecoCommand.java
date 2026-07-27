package com.hvs.webstore.back.app.command.webstore.preco;

public record ReadPrecoCommand(Long aId,
                               String aUuid) {

    public static ReadPrecoCommand from(final Long aId) {

        return new ReadPrecoCommand(
                aId,
                null
        );
    }

    public static ReadPrecoCommand from(final String aUuid) {

        return new ReadPrecoCommand(
                null,
                aUuid
        );
    }
}
