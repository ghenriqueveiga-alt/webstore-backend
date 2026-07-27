package com.hvs.webstore.back.domain.entity.webstore.pedido;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.Optional;

public interface PedidoDomainGateway {

    Pedido create(Pedido aPedido);

    Optional<Pedido> read(PedidoId aId);

    Optional<Pedido> readByUuid(PedidoUuid aUuid);

    Pagination<Pedido> readAll(SearchQuery aQuery);

    Pedido update(Pedido aPedido);

    Pedido patch(Pedido aPedido);

    void delete(Pedido aPedido);
}
