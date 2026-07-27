package com.hvs.webstore.back.domain.entity.webstore.produto;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.Optional;

public interface ProdutoDomainGateway {

    Produto create(Produto aProduto);

    Optional<Produto> read(ProdutoId aId);

    Optional<Produto> readByUuid(ProdutoUuid aUuid);

    Pagination<Produto> readAll(SearchQuery aQuery);

    Produto update(Produto aProduto);

    Produto patch(Produto aProduto);

    void delete(Produto aProduto);
}
