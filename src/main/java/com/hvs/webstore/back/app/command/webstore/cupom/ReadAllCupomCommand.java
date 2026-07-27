package com.hvs.webstore.back.app.command.webstore.cupom;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadAllCupomCommand(SearchQuery aSearchQuery) {

    public static ReadAllCupomCommand from(final SearchQuery aSearchQuery) {

        return new ReadAllCupomCommand(aSearchQuery);
    }
}
