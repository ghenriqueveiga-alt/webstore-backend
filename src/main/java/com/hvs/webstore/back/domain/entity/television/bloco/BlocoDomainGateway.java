package com.hvs.webstore.back.domain.entity.television.bloco;

import com.hvs.webstore.back.app.command.television.bloco.BlocoSearchQuery;
import com.hvs.webstore.back.domain.pagination.Pagination;

import java.util.Optional;

public interface BlocoDomainGateway {

    Bloco create(Bloco aBloco);

    Optional<Bloco> read(BlocoId aId);

    Optional<Bloco> readByUuid(BlocoUuid aUuid);

    Pagination<Bloco> readAll(BlocoSearchQuery aQuery);

    Bloco update(Bloco aBloco);

    Bloco patch(Bloco aBloco);

    void delete(Bloco aBloco);
}