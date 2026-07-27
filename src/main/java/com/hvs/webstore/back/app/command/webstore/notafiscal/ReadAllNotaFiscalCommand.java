package com.hvs.webstore.back.app.command.webstore.notafiscal;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadAllNotaFiscalCommand(SearchQuery aQuery) {

    public static ReadAllNotaFiscalCommand from(final SearchQuery aQuery) {

        return new ReadAllNotaFiscalCommand(aQuery);
    }
}
