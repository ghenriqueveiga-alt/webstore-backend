package com.hvs.webstore.back.app.command.webstore.metaloja;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadAllMetaLojaCommand(SearchQuery aQuery) {

    public static ReadAllMetaLojaCommand from(final SearchQuery aQuery) {

        return new ReadAllMetaLojaCommand(aQuery);
    }
}
