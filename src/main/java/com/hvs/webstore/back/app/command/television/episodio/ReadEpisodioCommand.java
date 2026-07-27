package com.hvs.webstore.back.app.command.television.episodio;

public record ReadEpisodioCommand(Long aId,
                                  String aUuid){

    public static ReadEpisodioCommand from(final Long aId) {

        return new ReadEpisodioCommand(aId, null);
    }

    public static ReadEpisodioCommand from(final String aUuid) {

        return new ReadEpisodioCommand(null, aUuid);
    }
}