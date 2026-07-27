package com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

public interface CategoriaHierarquiaDomainGateway {

    CategoriaHierarquia create(CategoriaHierarquia aCategoriaHierarquia);

    Optional<CategoriaHierarquia> read(CategoriaHierarquiaId aId);

    Optional<CategoriaHierarquia> readByUuid(CategoriaHierarquiaUuid aUuid);

    Pagination<CategoriaHierarquia> readAll(SearchQuery aQuery);

    List<CategoriaHierarquia> readByCategoriaId(Long aCategoriaId);

    List<CategoriaHierarquia> readByCategoriaPaiId(Long aCategoriaPaiId);

    Pagination<CategoriaHierarquia> readRoots(SearchQuery aQuery);

    CategoriaHierarquia update(CategoriaHierarquia aCategoriaHierarquia);

    CategoriaHierarquia patch(CategoriaHierarquia aCategoriaHierarquia);

    void delete(CategoriaHierarquia aCategoriaHierarquia);
}
