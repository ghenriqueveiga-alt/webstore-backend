package com.hvs.ws.back.app.output.episodio;

import com.hvs.ws.back.domain.entity.episodio.Episodio;

public record UpdateEpisodioOutput(Long aId,
                                   String aUuid,
                                   String aMessage) {

    public static UpdateEpisodioOutput from(final Episodio aEpisodio) {

        return new UpdateEpisodioOutput(
                aEpisodio.getId().getValue(),
                aEpisodio.getUuid().getValue(),
                "The Episode with id: " + aEpisodio.getUuid().getValue() + " has been successfully updated.");
    }
}
