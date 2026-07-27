package com.hvs.webstore.back.app.command.television.bloco;

public record ReadBlocoCommand(Long aId,
                               String aUuid){

    public static ReadBlocoCommand from(final Long aId) {

        return new ReadBlocoCommand(aId, null);
    }

    public static ReadBlocoCommand from(final String aUuid) {

        return new ReadBlocoCommand(null, aUuid);
    }
}