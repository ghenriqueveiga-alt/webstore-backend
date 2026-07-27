package com.hvs.webstore.back.app.output.webstore.endereco;

import com.hvs.webstore.back.domain.entity.webstore.endereco.Endereco;
import com.hvs.webstore.back.domain.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

public record ReadAllEnderecoOutput(int aCurrentPage,
                                    long aPerPage,
                                    long aTotal,
                                    List<ReadEnderecoOutput> aEnderecos) {

    public static ReadAllEnderecoOutput from(Pagination<Endereco> aPagination) {

        final List<ReadEnderecoOutput> list = new ArrayList<>();

        for (Endereco item : aPagination.aContent())
            list.add(ReadEnderecoOutput.from(item));

        return new ReadAllEnderecoOutput(
                aPagination.aPageNumber(),
                aPagination.aTotalElements(),
                aPagination.aTotalPages(),
                list);
    }
}
