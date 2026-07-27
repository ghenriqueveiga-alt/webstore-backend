package com.hvs.webstore.back.app.command.webstore.preferencianotificacao;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadAllPreferenciaNotificacaoCommand(SearchQuery aSearchQuery) {

    public static ReadAllPreferenciaNotificacaoCommand from(final SearchQuery aSearchQuery) {

        return new ReadAllPreferenciaNotificacaoCommand(aSearchQuery);
    }
}
