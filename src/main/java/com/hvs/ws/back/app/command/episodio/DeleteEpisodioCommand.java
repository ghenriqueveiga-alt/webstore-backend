package com.hvs.ws.back.app.command.episodio;

public record DeleteEpisodioCommand(Long aId,
                                    String aUuid) {

    public static DeleteEpisodioCommand from(final Long aId) {

        return new DeleteEpisodioCommand(aId, null);
    }

    public static DeleteEpisodioCommand from(final String aUuid) {

        return new DeleteEpisodioCommand(null, aUuid);
    }
}