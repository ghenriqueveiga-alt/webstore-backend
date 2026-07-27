package com.hvs.webstore.back.app.command.webstore.caracteristica;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadAllCaracteristicaCommand(SearchQuery aSearchQuery) {

    public static ReadAllCaracteristicaCommand from(final SearchQuery aSearchQuery) {

        return new ReadAllCaracteristicaCommand(aSearchQuery);
    }
}
