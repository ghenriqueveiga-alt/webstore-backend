package com.hvs.webstore.back.app.command.webstore.preco;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadAllPrecoCommand(SearchQuery aSearchQuery) {

    public static ReadAllPrecoCommand from(final SearchQuery aSearchQuery) {

        return new ReadAllPrecoCommand(aSearchQuery);
    }
}