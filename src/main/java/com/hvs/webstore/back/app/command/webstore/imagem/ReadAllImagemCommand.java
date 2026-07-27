package com.hvs.webstore.back.app.command.webstore.imagem;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadAllImagemCommand(SearchQuery aSearchQuery) {

    public static ReadAllImagemCommand from(final SearchQuery aSearchQuery) {

        return new ReadAllImagemCommand(aSearchQuery);
    }
}
