package com.hvs.webstore.back.domain.entity.television.programa;

import com.hvs.webstore.back.app.command.television.programa.ProgramaSearchQuery;
import com.hvs.webstore.back.domain.pagination.Pagination;

import java.util.Optional;

public interface ProgramaDomainGateway {

    Programa create(Programa aPrograma);

    Optional<Programa> read(ProgramaId aId);

    Optional<Programa> readByUuid(ProgramaUuid aUuid);

    Pagination<Programa> readAll(ProgramaSearchQuery aQuery);

    Programa update(Programa aPrograma);

    Programa patch(Programa aPrograma);

    void delete(Programa aPrograma);
}