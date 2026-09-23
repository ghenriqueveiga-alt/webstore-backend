package com.hvs.ws.back.app.command.episodio;

public record ReadAllEpisodioCommand(EpisodioSearchQuery aEpisodioSearchQuery) {

    public static ReadAllEpisodioCommand from(final EpisodioSearchQuery aEpisodioSearchQuery) {

        return new ReadAllEpisodioCommand(aEpisodioSearchQuery);
    }
}