package com.hvs.webstore.back.domain.entity.webstore.preco;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.Optional;

public interface PrecoDomainGateway {

    Preco create(Preco aPreco);

    Optional<Preco> read(PrecoId aId);

    Optional<Preco> readByUuid(PrecoUuid aUuid);

    Pagination<Preco> readAll(SearchQuery aQuery);

    Preco update(Preco aPreco);

    Preco patch(Preco aPreco);

    void delete(Preco aPreco);
}
