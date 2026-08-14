package com.hvs.webstore.back.app.output.television.arquivo;

import com.hvs.webstore.back.domain.entity.television.arquivo.Arquivo;
import com.hvs.webstore.back.domain.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

public record ReadAllArquivoOutput(int aCurrentPage,
                                   long aPerPage,
                                   long aTotal,
                                   List<ReadArquivoOutput> aArquivos) {

    public static ReadAllArquivoOutput from(final Pagination<Arquivo> aArquivoPagination) {

        final List<ReadArquivoOutput> list = new ArrayList<>();

        for (Arquivo aArquivo : aArquivoPagination.aContent()) {
            if (aArquivo.getStatusCode().getDesc().equals("Active")) {
                list.add(ReadArquivoOutput.from(aArquivo));
            }
        }

        return new ReadAllArquivoOutput(
                aArquivoPagination.aPageNumber(),
                aArquivoPagination.aContent().size(),
                aArquivoPagination.aTotalElements(),
                list);
    }
}
