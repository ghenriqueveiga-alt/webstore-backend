package com.hvs.webstore.back.domain.entity.webstore.cupom;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.Optional;

public interface CupomDomainGateway {

    Cupom create(Cupom aCupom);

    Optional<Cupom> read(CupomId aId);

    Optional<Cupom> readByUuid(CupomUuid aUuid);

    Pagination<Cupom> readAll(SearchQuery aQuery);

    Optional<Cupom> readByCodigo(String aCodigo);

    Cupom update(Cupom aCupom);

    Cupom patch(Cupom aCupom);

    void delete(Cupom aCupom);
}
