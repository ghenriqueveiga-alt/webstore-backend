package com.hvs.ws.back.domain.entity.arquivo;

import com.hvs.ws.back.app.command.arquivo.ArquivoSearchQuery;
import com.hvs.ws.back.domain.pagination.Pagination;

import java.util.Optional;

public interface ArquivoDomainGateway {

    Arquivo create(Arquivo aArquivo);

    Optional<Arquivo> read(ArquivoId aId);

    Optional<Arquivo> readByUuid(ArquivoUuid aUuid);

    Pagination<Arquivo> readAll(ArquivoSearchQuery aQuery);

    Arquivo update(Arquivo aArquivo);

    Arquivo patch(Arquivo aArquivo);

    void delete(Arquivo aArquivo);
}