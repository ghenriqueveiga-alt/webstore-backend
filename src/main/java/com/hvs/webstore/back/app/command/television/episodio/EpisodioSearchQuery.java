package com.hvs.webstore.back.app.command.television.episodio;

public record EpisodioSearchQuery(String aSearch,
                                  int aPage,
                                  int aSize,
                                  String aSort,
                                  String aDirection) {

    public static EpisodioSearchQuery from(final String aSearch,
                                           final int aPage,
                                           final int aSize,
                                           final String aSort,
                                           final String aDirection) {

        return new EpisodioSearchQuery(
                aSearch,
                aPage,
                aSize,
                aSort,
                aDirection);
    }
}