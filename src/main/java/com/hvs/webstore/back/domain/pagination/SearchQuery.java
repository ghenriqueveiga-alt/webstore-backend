package com.hvs.webstore.back.domain.pagination;

public record SearchQuery(String aSearch,
                          int aPage,
                          int aSize,
                          String aSort,
                          String aDirection) {

    public static SearchQuery from(String aSearch,
                                   int aPage,
                                   int aSize,
                                   String aSort,
                                   String aDirection) {

        return new SearchQuery(
                aSearch,
                aPage,
                aSize,
                aSort,
                aDirection
        );
    }
}
