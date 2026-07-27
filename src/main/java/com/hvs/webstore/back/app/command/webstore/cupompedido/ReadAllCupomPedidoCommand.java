package com.hvs.webstore.back.app.command.webstore.cupompedido;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadAllCupomPedidoCommand(SearchQuery aQuery) {

    public static ReadAllCupomPedidoCommand from(final SearchQuery aSearchQuery) {

        return new ReadAllCupomPedidoCommand(aSearchQuery);
    }
}
