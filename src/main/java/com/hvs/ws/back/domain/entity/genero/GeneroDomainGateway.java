package com.hvs.ws.back.domain.entity.genero;

import com.hvs.ws.back.app.command.genero.GeneroSearchQuery;
import com.hvs.ws.back.domain.pagination.Pagination;

import java.util.Optional;

public interface GeneroDomainGateway {

    Genero create(Genero aGenero);

    Optional<Genero> read(GeneroId aId);

    Optional<Genero> readByUuid(GeneroUuid aUuid);

    Pagination<Genero> readAll(GeneroSearchQuery aQuery);

    Genero update(Genero aGenero);

    Genero patch(Genero aGenero);

    void delete(Genero aGenero);
}
