package com.hvs.webstore.back.app.command.television.genero;

public record GeneroSearchQuery(String aSearch,
                                int aPage,
                                int aSize,
                                String aSort,
                                String aDirection) {

    public static GeneroSearchQuery from(final String aSearch,
                                         final int aPage,
                                         final int aSize,
                                         final String aSort,
                                         final String aDirection) {

        return new GeneroSearchQuery(
                aSearch,
                aPage,
                aSize,
                aSort,
                aDirection);
    }
}
