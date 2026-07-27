package com.hvs.webstore.back.app.command.television.programa;

public record ProgramaSearchQuery(String aSearch,
                                  int aPage,
                                  int aSize,
                                  String aSort,
                                  String aDirection) {

    public static ProgramaSearchQuery from(final String aSearch,
                                           final int aPage,
                                           final int aSize,
                                           final String aSort,
                                           final String aDirection) {

        return new ProgramaSearchQuery(
                aSearch,
                aPage,
                aSize,
                aSort,
                aDirection);
    }
}