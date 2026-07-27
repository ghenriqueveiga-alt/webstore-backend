package com.hvs.webstore.back.app.command.webstore.tokenverificacao;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadAllTokenVerificacaoCommand(SearchQuery aQuery) {

    public static ReadAllTokenVerificacaoCommand from(final SearchQuery aQuery) {

        return new ReadAllTokenVerificacaoCommand(aQuery);
    }
}
