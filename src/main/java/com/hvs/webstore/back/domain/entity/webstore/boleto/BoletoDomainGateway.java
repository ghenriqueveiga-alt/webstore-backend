package com.hvs.webstore.back.domain.entity.webstore.boleto;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.Optional;

public interface BoletoDomainGateway {

    Boleto create(Boleto aBoleto);

    Optional<Boleto> read(BoletoId aId);

    Optional<Boleto> readByUuid(BoletoUuid aUuid);

    Pagination<Boleto> readAll(SearchQuery aQuery);

    Boleto update(Boleto aBoleto);

    Boleto patch(Boleto aBoleto);

    void delete(Boleto aBoleto);
}
