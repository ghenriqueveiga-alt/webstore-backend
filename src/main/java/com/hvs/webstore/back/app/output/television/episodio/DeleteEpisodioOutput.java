package com.hvs.webstore.back.app.output.television.episodio;

import com.hvs.webstore.back.domain.entity.television.episodio.Episodio;

public record DeleteEpisodioOutput(Long aId,
                                   String aUuid,
                                   String aMessage) {

    public static DeleteEpisodioOutput from(final Episodio aEpisodio) {

        return new DeleteEpisodioOutput(
                aEpisodio.getId().getValue(),
                aEpisodio.getUuid().getValue(),
                "The Episode with id: " + aEpisodio.getUuid().getValue() + " has been successfully deleted.");
    }
}
