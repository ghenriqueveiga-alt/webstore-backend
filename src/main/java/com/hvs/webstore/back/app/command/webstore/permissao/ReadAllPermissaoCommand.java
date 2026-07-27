package com.hvs.webstore.back.app.command.webstore.permissao;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadAllPermissaoCommand(SearchQuery aQuery) {

    public static ReadAllPermissaoCommand from(final SearchQuery aQuery) {

        return new ReadAllPermissaoCommand(aQuery);
    }
}
