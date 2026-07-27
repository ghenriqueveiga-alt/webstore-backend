package com.hvs.webstore.back.app.command.webstore.carrinhofrete;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadAllCarrinhoFreteCommand(SearchQuery aSearchQuery) {

    public static ReadAllCarrinhoFreteCommand from(final SearchQuery aSearchQuery) {

        return new ReadAllCarrinhoFreteCommand(aSearchQuery);
    }
}
