package com.hvs.webstore.back.app.output.webstore.usuario;

import com.hvs.webstore.back.domain.entity.webstore.usuario.Usuario;
import com.hvs.webstore.back.domain.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

public record ReadAllUsuarioOutput(int aCurrentPage,
                                   long aPerPage,
                                   long aTotal,
                                   List<ReadUsuarioOutput> aUsuarios) {

    public static ReadAllUsuarioOutput from(Pagination<Usuario> aPagination) {

        var list = new ArrayList<ReadUsuarioOutput>();

        for (var i : aPagination.aContent())
            list.add(ReadUsuarioOutput.from(i));

        return new ReadAllUsuarioOutput(
                aPagination.aPageNumber(),
                aPagination.aTotalElements(),
                aPagination.aTotalPages(),
                list);
    }
}
