package com.hvs.webstore.back.app.command.webstore.boleto;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadAllBoletoCommand(SearchQuery aQuery) {

    public static ReadAllBoletoCommand from(final SearchQuery aQuery) {

        return new ReadAllBoletoCommand(aQuery);
    }
}
