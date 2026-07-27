package com.hvs.webstore.back.app.command.webstore.video;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadAllVideoCommand(SearchQuery aSearchQuery) {

    public static ReadAllVideoCommand from(final SearchQuery aSearchQuery) {

        return new ReadAllVideoCommand(aSearchQuery);
    }
}
