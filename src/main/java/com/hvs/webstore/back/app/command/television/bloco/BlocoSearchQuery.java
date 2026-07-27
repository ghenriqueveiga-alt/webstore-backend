package com.hvs.webstore.back.app.command.television.bloco;

public record BlocoSearchQuery(String aSearch,
                               int aPage,
                               int aSize,
                               String aSort,
                               String aDirection) {

    public static BlocoSearchQuery from(final String aSearch,
                                        final int aPage,
                                        final int aSize,
                                        final String aSort,
                                        final String aDirection) {

        return new BlocoSearchQuery(
                aSearch,
                aPage,
                aSize,
                aSort,
                aDirection);
    }
}