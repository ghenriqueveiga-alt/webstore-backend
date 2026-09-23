package com.hvs.ws.back.domain.entity.programa;

import com.hvs.ws.back.app.command.programa.ProgramaSearchQuery;
import com.hvs.ws.back.domain.pagination.Pagination;

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