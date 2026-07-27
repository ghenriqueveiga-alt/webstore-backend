package com.hvs.webstore.back.app.command.webstore.marca;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadAllMarcaCommand(SearchQuery aSearchQuery) {

    public static ReadAllMarcaCommand from(final SearchQuery aSearchQuery) {

        return new ReadAllMarcaCommand(aSearchQuery);
    }
}
