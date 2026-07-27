package com.hvs.webstore.back.app.command.television.bloco;

public record DeleteBlocoCommand(Long aId, String aUuid) {

    public static DeleteBlocoCommand from(final Long aId) {

        return new DeleteBlocoCommand(aId, null);
    }

    public static DeleteBlocoCommand from(final String aUuid) {

        return new DeleteBlocoCommand(null, aUuid);
    }
}