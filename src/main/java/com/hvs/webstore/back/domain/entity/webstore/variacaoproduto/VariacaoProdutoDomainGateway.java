package com.hvs.webstore.back.domain.entity.webstore.variacaoproduto;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

public interface VariacaoProdutoDomainGateway {

    VariacaoProduto create(VariacaoProduto aVariacaoProduto);

    Optional<VariacaoProduto> read(VariacaoProdutoId aId);

    Optional<VariacaoProduto> readByUuid(VariacaoProdutoUuid aUuid);

    Pagination<VariacaoProduto> readAll(SearchQuery aQuery);

    List<VariacaoProduto> readByProdutoId(Long aProdutoId);

    VariacaoProduto update(VariacaoProduto aVariacaoProduto);

    VariacaoProduto patch(VariacaoProduto aVariacaoProduto);

    void delete(VariacaoProduto aVariacaoProduto);
}
