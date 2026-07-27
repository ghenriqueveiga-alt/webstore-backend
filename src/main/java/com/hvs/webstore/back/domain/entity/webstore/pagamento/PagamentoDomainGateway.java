package com.hvs.webstore.back.domain.entity.webstore.pagamento;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.Optional;

public interface PagamentoDomainGateway {

    Pagamento create(Pagamento aPagamento);

    Optional<Pagamento> read(PagamentoId aId);

    Optional<Pagamento> readByUuid(PagamentoUuid aUuid);

    Optional<Pagamento> readByPedidoId(Long aPedidoId);

    Pagination<Pagamento> readAll(SearchQuery aQuery);

    Pagamento update(Pagamento aPagamento);

    Pagamento patch(Pagamento aPagamento);

    void delete(Pagamento aPagamento);
}
