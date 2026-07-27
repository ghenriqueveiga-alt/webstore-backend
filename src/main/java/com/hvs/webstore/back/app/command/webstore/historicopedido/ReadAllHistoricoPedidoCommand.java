package com.hvs.webstore.back.app.command.webstore.historicopedido;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadAllHistoricoPedidoCommand(SearchQuery aQuery) {

    public static ReadAllHistoricoPedidoCommand from(final SearchQuery aSearchQuery) {

        return new ReadAllHistoricoPedidoCommand(aSearchQuery);
    }
}
