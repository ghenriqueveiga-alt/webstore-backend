package com.hvs.webstore.back.domain.entity.webstore.precopromocional;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

public interface PrecoPromocionalDomainGateway {

    PrecoPromocional create(PrecoPromocional aPrecoPromocional);

    Optional<PrecoPromocional> read(PrecoPromocionalId aId);

    Optional<PrecoPromocional> readByUuid(PrecoPromocionalUuid aUuid);

    Pagination<PrecoPromocional> readAll(SearchQuery aQuery);

    List<PrecoPromocional> readByProdutoId(Long aProdutoId);

    PrecoPromocional update(PrecoPromocional aPrecoPromocional);

    PrecoPromocional patch(PrecoPromocional aPrecoPromocional);

    void delete(PrecoPromocional aPrecoPromocional);
}
