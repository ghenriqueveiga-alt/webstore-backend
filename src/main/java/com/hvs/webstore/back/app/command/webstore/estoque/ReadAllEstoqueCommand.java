package com.hvs.webstore.back.app.command.webstore.estoque;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadAllEstoqueCommand(SearchQuery aSearchQuery) {

    public static ReadAllEstoqueCommand from(final SearchQuery aSearchQuery) {

        return new ReadAllEstoqueCommand(aSearchQuery);
    }
}
