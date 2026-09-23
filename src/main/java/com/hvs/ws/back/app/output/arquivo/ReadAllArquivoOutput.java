package com.hvs.ws.back.app.output.arquivo;

import com.hvs.ws.back.domain.entity.arquivo.Arquivo;
import com.hvs.ws.back.domain.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

public record ReadAllArquivoOutput(int aCurrentPage,
                                   long aPerPage,
                                   long aTotal,
                                   List<ReadArquivoOutput> aArquivos) {

    public static ReadAllArquivoOutput from(final Pagination<Arquivo> aArquivoPagination) {

        final List<ReadArquivoOutput> list = new ArrayList<>();

        for (Arquivo aArquivo : aArquivoPagination.aContent()) {
            if (aArquivo.getStatus().getDesc().equals("Active")) {
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
