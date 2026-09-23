package com.hvs.ws.back.app.command.bloco;

public record DeleteBlocoCommand(Long aId, String aUuid) {

    public static DeleteBlocoCommand from(final Long aId) {

        return new DeleteBlocoCommand(aId, null);
    }

    public static DeleteBlocoCommand from(final String aUuid) {

        return new DeleteBlocoCommand(null, aUuid);
    }
}