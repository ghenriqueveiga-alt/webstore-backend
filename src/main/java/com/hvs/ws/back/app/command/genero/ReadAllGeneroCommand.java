package com.hvs.ws.back.app.command.genero;

public record ReadAllGeneroCommand(GeneroSearchQuery aGeneroSearchQuery) {

    public static ReadAllGeneroCommand from(final GeneroSearchQuery aGeneroSearchQuery) {

        return new ReadAllGeneroCommand(aGeneroSearchQuery);
    }
}
