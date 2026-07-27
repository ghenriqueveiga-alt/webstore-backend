package com.hvs.webstore.back.domain.entity.webstore.cartao;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.Optional;

public interface CartaoDomainGateway {

    Cartao create(Cartao aCartao);

    Optional<Cartao> read(CartaoId aId);

    Optional<Cartao> readByUuid(CartaoUuid aUuid);

    Pagination<Cartao> readAll(SearchQuery aQuery);

    Cartao update(Cartao aCartao);

    Cartao patch(Cartao aCartao);

    void delete(Cartao aCartao);
}
