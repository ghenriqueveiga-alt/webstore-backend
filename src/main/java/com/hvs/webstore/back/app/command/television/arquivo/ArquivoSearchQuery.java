package com.hvs.webstore.back.app.command.television.arquivo;

public record ArquivoSearchQuery(String aSearch,
                                 int aPage,
                                 int aSize,
                                 String aSort,
                                 String aDirection) {

    public static ArquivoSearchQuery from(final String aSearch,
                                          final int aPage,
                                          final int aSize,
                                          final String aSort,
                                          final String aDirection) {

        return new ArquivoSearchQuery(
                aSearch,
                aPage,
                aSize,
                aSort,
                aDirection);
    }
}