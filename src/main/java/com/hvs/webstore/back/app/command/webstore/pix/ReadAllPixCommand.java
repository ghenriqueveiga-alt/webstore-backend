package com.hvs.webstore.back.app.command.webstore.pix;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadAllPixCommand(SearchQuery aQuery) {

    public static ReadAllPixCommand from(final SearchQuery aQuery) {

        return new ReadAllPixCommand(aQuery);
    }
}
