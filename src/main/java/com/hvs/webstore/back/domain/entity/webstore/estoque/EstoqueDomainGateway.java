package com.hvs.webstore.back.domain.entity.webstore.estoque;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.Optional;

public interface EstoqueDomainGateway {

    Estoque create(Estoque aEstoque);

    MovimentoEstoque createMovimento(MovimentoEstoque aMovimento);

    Optional<Estoque> read(EstoqueId aId);

    Optional<Estoque> readByUuid(EstoqueUuid aUuid);

    Pagination<Estoque> readAll(SearchQuery aQuery);

    Optional<Estoque> findByProduto(Long aProdutoId);

    Estoque update(Estoque aEstoque);

    Estoque patch(Estoque aEstoque);

    void delete(Estoque aEstoque);
}
