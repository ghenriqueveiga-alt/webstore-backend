package com.hvs.webstore.back.domain.entity.webstore.anexo;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

public interface AnexoDomainGateway {

    Anexo create(Anexo aAnexo);

    Optional<Anexo> read(AnexoId aId);

    Optional<Anexo> readByUuid(AnexoUuid aUuid);

    Pagination<Anexo> readAll(SearchQuery aQuery);

    List<Anexo> readByEntidade(String entidadeNome);

    Anexo update(Anexo aAnexo);

    Anexo patch(Anexo aAnexo);

    void delete(Anexo aAnexo);
}
