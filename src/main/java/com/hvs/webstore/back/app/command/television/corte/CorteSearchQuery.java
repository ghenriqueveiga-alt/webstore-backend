package com.hvs.webstore.back.app.command.television.corte;

public record CorteSearchQuery(String aSearch,
                               int aPage,
                               int aSize,
                               String aSort,
                               String aDirection) {

    public static CorteSearchQuery from(final String aSearch,
                                        final int aPage,
                                        final int aSize,
                                        final String aSort,
                                        final String aDirection) {

        return new CorteSearchQuery(
                aSearch,
                aPage,
                aSize,
                aSort,
                aDirection);
    }
}