package com.hvs.webstore.back.domain.entity.television.arquivo;

import com.hvs.webstore.back.app.command.television.arquivo.ArquivoSearchQuery;
import com.hvs.webstore.back.domain.pagination.Pagination;

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