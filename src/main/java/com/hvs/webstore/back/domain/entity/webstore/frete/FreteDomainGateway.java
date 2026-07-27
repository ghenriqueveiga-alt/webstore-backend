package com.hvs.webstore.back.domain.entity.webstore.frete;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.Optional;

public interface FreteDomainGateway {

    Frete create(Frete aFrete);

    Optional<Frete> read(FreteId aId);

    Optional<Frete> readByUuid(FreteUuid aUuid);

    Pagination<Frete> readAll(SearchQuery aQuery);

    Frete update(Frete aFrete);

    Frete patch(Frete aFrete);

    void delete(Frete aFrete);
}
