package com.hvs.webstore.back.app.command.television.genero;

public record ReadAllGeneroCommand(GeneroSearchQuery aGeneroSearchQuery) {

    public static ReadAllGeneroCommand from(final GeneroSearchQuery aGeneroSearchQuery) {

        return new ReadAllGeneroCommand(aGeneroSearchQuery);
    }
}
