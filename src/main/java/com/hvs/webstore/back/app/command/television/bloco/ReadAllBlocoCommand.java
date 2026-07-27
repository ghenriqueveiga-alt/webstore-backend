package com.hvs.webstore.back.app.command.television.bloco;

public record ReadAllBlocoCommand(BlocoSearchQuery aBlocoSearchQuery) {

    public static ReadAllBlocoCommand from(final BlocoSearchQuery aBlocoSearchQuery) {

        return new ReadAllBlocoCommand(aBlocoSearchQuery);
    }
}