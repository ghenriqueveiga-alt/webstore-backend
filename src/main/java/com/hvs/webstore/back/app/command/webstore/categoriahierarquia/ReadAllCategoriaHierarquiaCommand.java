package com.hvs.webstore.back.app.command.webstore.categoriahierarquia;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadAllCategoriaHierarquiaCommand(SearchQuery aSearchQuery) {

    public static ReadAllCategoriaHierarquiaCommand from(final SearchQuery aSearchQuery) {

        return new ReadAllCategoriaHierarquiaCommand(aSearchQuery);
    }
}
