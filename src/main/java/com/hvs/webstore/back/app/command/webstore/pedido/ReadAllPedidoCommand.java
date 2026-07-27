package com.hvs.webstore.back.app.command.webstore.pedido;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadAllPedidoCommand(SearchQuery aSearchQuery) {

    public static ReadAllPedidoCommand from(final SearchQuery aSearchQuery) {

        return new ReadAllPedidoCommand(aSearchQuery);
    }
}
