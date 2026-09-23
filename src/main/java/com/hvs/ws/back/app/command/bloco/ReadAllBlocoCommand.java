package com.hvs.ws.back.app.command.bloco;

public record ReadAllBlocoCommand(BlocoSearchQuery aBlocoSearchQuery) {

    public static ReadAllBlocoCommand from(final BlocoSearchQuery aBlocoSearchQuery) {

        return new ReadAllBlocoCommand(aBlocoSearchQuery);
    }
}