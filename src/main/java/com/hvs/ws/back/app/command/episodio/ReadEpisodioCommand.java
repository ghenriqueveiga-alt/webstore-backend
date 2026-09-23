package com.hvs.ws.back.app.command.episodio;

public record ReadEpisodioCommand(Long aId,
                                  String aUuid){

    public static ReadEpisodioCommand from(final Long aId) {

        return new ReadEpisodioCommand(aId, null);
    }

    public static ReadEpisodioCommand from(final String aUuid) {

        return new ReadEpisodioCommand(null, aUuid);
    }
}