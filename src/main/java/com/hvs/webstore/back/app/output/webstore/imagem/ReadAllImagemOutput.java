package com.hvs.webstore.back.app.output.webstore.imagem;

import com.hvs.webstore.back.domain.entity.webstore.imagem.Imagem;
import com.hvs.webstore.back.domain.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

public record ReadAllImagemOutput(int aCurrentPage,
                                  long aPerPage,
                                  long aTotal,
                                  List<ReadImagemOutput> aImagens) {

    public static ReadAllImagemOutput from(Pagination<Imagem> aPagination) {

        final List<ReadImagemOutput> list = new ArrayList<>();

        for (var i : aPagination.aContent())
            list.add(ReadImagemOutput.from(i));

        return new ReadAllImagemOutput(
                aPagination.aPageNumber(),
                aPagination.aContent().size(),
                aPagination.aTotalElements(),
                list);
    }
}
