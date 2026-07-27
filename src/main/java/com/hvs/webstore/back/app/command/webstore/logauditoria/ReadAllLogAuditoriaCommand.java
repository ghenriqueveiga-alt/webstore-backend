package com.hvs.webstore.back.app.command.webstore.logauditoria;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadAllLogAuditoriaCommand(SearchQuery aSearchQuery) {

    public static ReadAllLogAuditoriaCommand from(final SearchQuery aSearchQuery) {

        return new ReadAllLogAuditoriaCommand(aSearchQuery);
    }
}
