package com.hvs.webstore.back.app.command.webstore.variacaoproduto;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadAllVariacaoProdutoCommand(SearchQuery aQuery) {

    public static ReadAllVariacaoProdutoCommand from(final SearchQuery aQuery) {

        return new ReadAllVariacaoProdutoCommand(aQuery);
    }
}
