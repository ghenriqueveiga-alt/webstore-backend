package com.hvs.webstore.back.app.command.webstore.precopromocional;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadAllPrecoPromocionalCommand(SearchQuery aQuery) {

    public static ReadAllPrecoPromocionalCommand from(final SearchQuery aSearchQuery) {

        return new ReadAllPrecoPromocionalCommand(aSearchQuery);
    }
}
