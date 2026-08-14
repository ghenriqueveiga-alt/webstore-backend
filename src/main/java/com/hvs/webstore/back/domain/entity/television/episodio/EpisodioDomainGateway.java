package com.hvs.webstore.back.domain.entity.television.episodio;

import com.hvs.webstore.back.app.command.television.episodio.EpisodioSearchQuery;
import com.hvs.webstore.back.domain.pagination.Pagination;

import java.util.List;
import java.util.Optional;

public interface EpisodioDomainGateway {

    Episodio create(Episodio aEpisodio);

    Optional<Episodio> read(EpisodioId aId);

    Optional<Episodio> readByUuid(EpisodioUuid aUuid);

    Pagination<Episodio> readAll(EpisodioSearchQuery aQuery);

    List<Episodio> readByPrograma(Long aProgramaId);

    Episodio update(Episodio aEpisodio);

    Episodio patch(Episodio aEpisodio);

    void delete(Episodio aEpisodio);
}