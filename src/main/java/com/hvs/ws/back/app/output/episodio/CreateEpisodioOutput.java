package com.hvs.ws.back.app.output.episodio;

import com.hvs.ws.back.domain.entity.episodio.Episodio;

public record CreateEpisodioOutput(Long aId,
                                   String aUuid,
                                   String aMessage) {

    public static CreateEpisodioOutput from(final Episodio aEpisode) {

        return new CreateEpisodioOutput(
                aEpisode.getId().getValue(),
                aEpisode.getUuid().getValue(),
                "The Episode with id: " + aEpisode.getUuid().getValue() + " has been successfully created.");
    }
}
