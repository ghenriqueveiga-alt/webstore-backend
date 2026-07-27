package com.hvs.webstore.back.app.command.webstore.endereco;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadAllEnderecoCommand(SearchQuery aSearchQuery) {

    public static ReadAllEnderecoCommand from(final SearchQuery aSearchQuery) {

        return new ReadAllEnderecoCommand(aSearchQuery);
    }
}
