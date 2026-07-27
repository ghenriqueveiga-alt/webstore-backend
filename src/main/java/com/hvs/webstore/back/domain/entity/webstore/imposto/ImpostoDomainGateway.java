package com.hvs.webstore.back.domain.entity.webstore.imposto;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.Optional;

public interface ImpostoDomainGateway {

    Imposto create(Imposto aImposto);

    Optional<Imposto> read(ImpostoId aId);

    Optional<Imposto> readByUuid(ImpostoUuid aUuid);

    Pagination<Imposto> readAll(SearchQuery aQuery);

    Imposto update(Imposto aImposto);

    Imposto patch(Imposto aImposto);

    void delete(Imposto aImposto);
}
