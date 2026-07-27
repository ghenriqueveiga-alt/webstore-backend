package com.hvs.webstore.back.app.command.webstore.formapagamento;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadAllFormaPagamentoCommand(SearchQuery aSearchQuery) {

    public static ReadAllFormaPagamentoCommand from(final SearchQuery aSearchQuery) {

        return new ReadAllFormaPagamentoCommand(aSearchQuery);
    }
}
