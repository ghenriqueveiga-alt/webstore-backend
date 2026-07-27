package com.hvs.webstore.back.app.command.webstore.role;

import com.hvs.webstore.back.domain.pagination.SearchQuery;

public record ReadAllRoleCommand(SearchQuery aQuery) {

    public static ReadAllRoleCommand from(final SearchQuery aQuery) {

        return new ReadAllRoleCommand(aQuery);
    }
}
