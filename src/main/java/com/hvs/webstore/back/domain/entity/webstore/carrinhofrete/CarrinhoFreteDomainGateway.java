package com.hvs.webstore.back.domain.entity.webstore.carrinhofrete;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

public interface CarrinhoFreteDomainGateway {

    CarrinhoFrete create(CarrinhoFrete aCarrinhoFrete);

    Optional<CarrinhoFrete> read(CarrinhoFreteId aId);

    Optional<CarrinhoFrete> readByUuid(CarrinhoFreteUuid aUuid);

    Pagination<CarrinhoFrete> readAll(SearchQuery aQuery);

    List<CarrinhoFrete> readByCarrinhoId(Long aCarrinhoId);

    CarrinhoFrete update(CarrinhoFrete aCarrinhoFrete);

    CarrinhoFrete patch(CarrinhoFrete aCarrinhoFrete);

    void delete(CarrinhoFrete aCarrinhoFrete);
}
