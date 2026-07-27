package com.hvs.webstore.back.domain.entity.webstore.cupompedido;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

public interface CupomPedidoDomainGateway {

    CupomPedido create(CupomPedido aCupomPedido);

    Optional<CupomPedido> read(CupomPedidoId aId);

    Optional<CupomPedido> readByUuid(CupomPedidoUuid aUuid);

    Pagination<CupomPedido> readAll(SearchQuery aQuery);

    List<CupomPedido> readByPedidoId(Long aPedidoId);

    List<CupomPedido> readByCupomId(Long aCupomId);

    CupomPedido update(CupomPedido aCupomPedido);

    CupomPedido patch(CupomPedido aCupomPedido);

    void delete(CupomPedido aCupomPedido);
}
