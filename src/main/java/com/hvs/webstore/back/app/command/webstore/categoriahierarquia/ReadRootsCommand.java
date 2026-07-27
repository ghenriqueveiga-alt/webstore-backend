package com.hvs.webstore.back.app.command.webstore.categoriahierarquia;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadRootsCommand(SearchQuery aSearchQuery) {

    public static ReadRootsCommand from(final SearchQuery aSearchQuery) {

        return new ReadRootsCommand(aSearchQuery);
    }
}
