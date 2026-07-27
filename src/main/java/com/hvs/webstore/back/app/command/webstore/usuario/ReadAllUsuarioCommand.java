package com.hvs.webstore.back.app.command.webstore.usuario;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadAllUsuarioCommand(SearchQuery aSearchQuery) {

    public static ReadAllUsuarioCommand from(final SearchQuery aSearchQuery) {

        return new ReadAllUsuarioCommand(aSearchQuery);
    }
}
