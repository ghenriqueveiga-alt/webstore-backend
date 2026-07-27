package com.hvs.webstore.back.domain.entity.webstore.categoria;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.Optional;

public interface CategoriaDomainGateway {

    Categoria create(Categoria aCategoria);

    Optional<Categoria> read(CategoriaId aId);

    Optional<Categoria> readByUuid(CategoriaUuid aUuid);

    Pagination<Categoria> readAll(SearchQuery aQuery);

    Categoria update(Categoria aCategoria);

    Categoria patch(Categoria aCategoria);

    void delete(Categoria aCategoria);
}
