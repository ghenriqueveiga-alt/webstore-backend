package com.hvs.webstore.back.app.output.webstore.permissao;

import com.hvs.webstore.back.domain.entity.webstore.permissao.Permissao;
import com.hvs.webstore.back.domain.pagination.Pagination;
import java.util.ArrayList;
import java.util.List;

public record ReadAllPermissaoOutput(int aCurrentPage,
                                     long aPerPage,
                                     long aTotal, List<ReadPermissaoOutput> aPermissoes) {

    public static ReadAllPermissaoOutput from(Pagination<Permissao> p) {

        var list = new ArrayList<ReadPermissaoOutput>();

        for (var i : p.aContent())
            list.add(ReadPermissaoOutput.from(i));

        return new ReadAllPermissaoOutput(
                p.aPageNumber(),
                p.aContent().size(),
                p.aTotalElements(),
                list);
    }
}
