package com.hvs.webstore.back.app.command.television.episodio;

import java.util.List;

public record EpisodioSearchQuery(String aSearch,
                                  Long aProgramaId,
                                  List<Long> aProgramaIds,
                                  int aPage,
                                  int aSize,
                                  String aSort,
                                  String aDirection) {

    public static EpisodioSearchQuery from(final String aSearch,
                                           final Long aProgramaId,
                                           final List<Long> aProgramaIds,
                                           final int aPage,
                                           final int aSize,
                                           final String aSort,
                                           final String aDirection) {

        return new EpisodioSearchQuery(
                aSearch,
                aProgramaId,
                aProgramaIds,
                aPage,
                aSize,
                aSort,
                aDirection);
    }
}