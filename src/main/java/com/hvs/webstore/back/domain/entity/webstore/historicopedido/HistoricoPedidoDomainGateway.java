package com.hvs.webstore.back.domain.entity.webstore.historicopedido;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

public interface HistoricoPedidoDomainGateway {

    HistoricoPedido create(HistoricoPedido aHistoricoPedido);

    Optional<HistoricoPedido> read(HistoricoPedidoId aId);

    Optional<HistoricoPedido> readByUuid(HistoricoPedidoUuid aUuid);

    Pagination<HistoricoPedido> readAll(SearchQuery aQuery);

    List<HistoricoPedido> readByPedidoId(Long aPedidoId);

    HistoricoPedido update(HistoricoPedido aHistoricoPedido);

    HistoricoPedido patch(HistoricoPedido aHistoricoPedido);

    void delete(HistoricoPedido aHistoricoPedido);
}
