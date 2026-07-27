package com.hvs.webstore.back.app.command.webstore.avaliacao;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadAllAvaliacaoCommand(SearchQuery aSearchQuery) {

    public static ReadAllAvaliacaoCommand from(final SearchQuery aSearchQuery) {

        return new ReadAllAvaliacaoCommand(aSearchQuery);
    }
}
