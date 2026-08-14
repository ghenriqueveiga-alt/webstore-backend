package com.hvs.webstore.back.domain.entity.television.genero;

import com.hvs.webstore.back.app.command.television.genero.GeneroSearchQuery;
import com.hvs.webstore.back.domain.pagination.Pagination;

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
