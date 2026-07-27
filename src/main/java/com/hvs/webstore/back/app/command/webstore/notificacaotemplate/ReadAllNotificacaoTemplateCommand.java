package com.hvs.webstore.back.app.command.webstore.notificacaotemplate;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadAllNotificacaoTemplateCommand(SearchQuery aSearchQuery) {

    public static ReadAllNotificacaoTemplateCommand from(final SearchQuery aSearchQuery) {

        return new ReadAllNotificacaoTemplateCommand(aSearchQuery);
    }
}
