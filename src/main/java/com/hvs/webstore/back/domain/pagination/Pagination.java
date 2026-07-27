package com.hvs.webstore.back.domain.pagination;

import java.util.List;

public record Pagination<T>(int aPageNumber,
                            long aTotalElements,
                            long aTotalPages,
                            List<T> aContent) {

    public static <T> Pagination<T> from(final int pageNumber,
                                         final long totalElements,
                                         final long totalPages,
                                         final List<T> content) {

        return new Pagination<>(
                pageNumber,
                totalElements,
                totalPages,
                content);
    }
}
