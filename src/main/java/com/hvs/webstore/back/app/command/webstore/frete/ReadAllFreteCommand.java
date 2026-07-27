package com.hvs.webstore.back.app.command.webstore.frete;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadAllFreteCommand(SearchQuery aSearchQuery) {

    public static ReadAllFreteCommand from(final SearchQuery aSearchQuery) {

        return new ReadAllFreteCommand(aSearchQuery);
    }
}
