package com.hvs.webstore.back.domain.entity.television.corte;

import com.hvs.webstore.back.app.command.television.corte.CorteSearchQuery;
import com.hvs.webstore.back.domain.pagination.Pagination;

import java.util.Optional;

public interface CorteDomainGateway {

    Corte create(Corte aCorte);

    Optional<Corte> read(CorteId aId);

    Optional<Corte> readByUuid(CorteUuid aUuid);

    Pagination<Corte> readAll(CorteSearchQuery aQuery);

    Corte update(Corte aCorte);

    Corte patch(Corte aCorte);

    void delete(Corte aCorte);
}