package com.hvs.webstore.back.app.command.webstore.produto;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadAllProdutoCommand(SearchQuery aSearchQuery) {

    public static ReadAllProdutoCommand from(final SearchQuery aSearchQuery) {

        return new ReadAllProdutoCommand(aSearchQuery);
    }
}
