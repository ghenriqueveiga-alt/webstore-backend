package com.hvs.webstore.back.app.command.webstore.preco;

public record DeletePrecoCommand(Long aId,
                                 String aUuid) {

    public static DeletePrecoCommand from(final Long aId) {

        return new DeletePrecoCommand(
                aId,
                null);
    }

    public static DeletePrecoCommand from(final String aUuid) {

        return new DeletePrecoCommand(
                null,
                aUuid);
    }
}
