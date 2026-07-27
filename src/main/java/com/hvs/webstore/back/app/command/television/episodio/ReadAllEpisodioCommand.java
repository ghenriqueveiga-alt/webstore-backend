package com.hvs.webstore.back.app.command.television.episodio;

public record ReadAllEpisodioCommand(EpisodioSearchQuery aEpisodioSearchQuery) {

    public static ReadAllEpisodioCommand from(final EpisodioSearchQuery aEpisodioSearchQuery) {

        return new ReadAllEpisodioCommand(aEpisodioSearchQuery);
    }
}