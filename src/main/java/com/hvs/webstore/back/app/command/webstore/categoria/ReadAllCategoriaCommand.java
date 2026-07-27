package com.hvs.webstore.back.app.command.webstore.categoria;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadAllCategoriaCommand(SearchQuery aSearchQuery) {

    public static ReadAllCategoriaCommand from(final SearchQuery aSearchQuery) {

        return new ReadAllCategoriaCommand(aSearchQuery);
    }
}
