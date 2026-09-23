package com.hvs.ws.back.app.output.episodio;

import com.hvs.ws.back.domain.entity.episodio.Episodio;
import com.hvs.ws.back.domain.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

public record ReadAllEpisodioOutput(int aCurrentPage,
                                    long aPerPage,
                                    long aTotal,
                                    List<ReadEpisodioOutput> aEpisodios) {

    public static ReadAllEpisodioOutput from(final Pagination<Episodio> aCortePagination) {

        final List<ReadEpisodioOutput> list = new ArrayList<>();

        for (Episodio aEpisodio : aCortePagination.aContent()) {
            list.add(ReadEpisodioOutput.from(aEpisodio));
        }

        return new ReadAllEpisodioOutput(
                aCortePagination.aPageNumber(),
                aCortePagination.aContent().size(),
                aCortePagination.aTotalElements(),
                list);
    }
}
