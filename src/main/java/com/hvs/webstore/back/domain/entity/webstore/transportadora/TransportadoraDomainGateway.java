package com.hvs.webstore.back.domain.entity.webstore.transportadora;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.Optional;

public interface TransportadoraDomainGateway {

    Transportadora create(Transportadora aTransportadora);

    Optional<Transportadora> read(TransportadoraId aId);

    Optional<Transportadora> readByUuid(TransportadoraUuid aUuid);

    Pagination<Transportadora> readAll(SearchQuery aQuery);

    Transportadora update(Transportadora aTransportadora);

    Transportadora patch(Transportadora aTransportadora);

    void delete(Transportadora aTransportadora);
}
