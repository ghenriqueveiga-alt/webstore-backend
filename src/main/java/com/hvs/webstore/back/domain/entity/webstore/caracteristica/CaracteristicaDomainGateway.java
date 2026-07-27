package com.hvs.webstore.back.domain.entity.webstore.caracteristica;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.Optional;

public interface CaracteristicaDomainGateway {

    Caracteristica create(Caracteristica aCaracteristica);

    Optional<Caracteristica> read(CaracteristicaId aId);

    Optional<Caracteristica> readByUuid(CaracteristicaUuid aUuid);

    Pagination<Caracteristica> readAll(SearchQuery aQuery);

    Caracteristica update(Caracteristica aCaracteristica);

    Caracteristica patch(Caracteristica aCaracteristica);

    void delete(Caracteristica aCaracteristica);
}
