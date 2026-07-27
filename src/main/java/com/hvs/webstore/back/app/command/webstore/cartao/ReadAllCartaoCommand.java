package com.hvs.webstore.back.app.command.webstore.cartao;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadAllCartaoCommand(SearchQuery aSearchQuery) {

    public static ReadAllCartaoCommand from(final SearchQuery aSearchQuery) {

        return new ReadAllCartaoCommand(aSearchQuery);
    }
}
