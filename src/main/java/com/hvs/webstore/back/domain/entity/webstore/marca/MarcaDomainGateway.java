package com.hvs.webstore.back.domain.entity.webstore.marca;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.Optional;

public interface MarcaDomainGateway {

    Marca create(Marca aMarca);

    Optional<Marca> read(MarcaId aId);

    Optional<Marca> readByUuid(MarcaUuid aUuid);

    Pagination<Marca> readAll(SearchQuery aQuery);

    Marca update(Marca aMarca);

    Marca patch(Marca aMarca);

    void delete(Marca aMarca);
}
