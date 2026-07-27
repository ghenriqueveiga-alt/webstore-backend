package com.hvs.webstore.back.app.output.television.episodio;

import com.hvs.webstore.back.domain.entity.television.episodio.Episodio;

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
