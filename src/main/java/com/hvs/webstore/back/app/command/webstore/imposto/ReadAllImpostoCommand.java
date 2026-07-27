package com.hvs.webstore.back.app.command.webstore.imposto;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadAllImpostoCommand(SearchQuery aQuery) {

    public static ReadAllImpostoCommand from(final SearchQuery aQuery) {

        return new ReadAllImpostoCommand(aQuery);
    }
}
