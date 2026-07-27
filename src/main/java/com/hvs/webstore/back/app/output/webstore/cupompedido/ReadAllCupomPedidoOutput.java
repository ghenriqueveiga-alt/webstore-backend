package com.hvs.webstore.back.app.output.webstore.cupompedido;

import com.hvs.webstore.back.domain.entity.webstore.cupompedido.CupomPedido;
import com.hvs.webstore.back.domain.pagination.Pagination;
import java.util.ArrayList;
import java.util.List;

public record ReadAllCupomPedidoOutput(int aCurrentPage,
                                       long aPerPage,
                                       long aTotal,
                                       List<ReadCupomPedidoOutput> aCupons) {

    public static ReadAllCupomPedidoOutput from(Pagination<CupomPedido> aPagination) {

        final List<ReadCupomPedidoOutput> list = new ArrayList<>();

        for (CupomPedido item : aPagination.aContent())
            list.add(ReadCupomPedidoOutput.from(item));

        return new ReadAllCupomPedidoOutput(
                aPagination.aPageNumber(),
                aPagination.aTotalElements(),
                aPagination.aTotalPages(),
                list);
    }

    public static ReadAllCupomPedidoOutput from(List<CupomPedido> aCupomPedido) {

        final List<ReadCupomPedidoOutput> list = new ArrayList<>();

        for (CupomPedido item : aCupomPedido)
            list.add(ReadCupomPedidoOutput.from(item));

        return new ReadAllCupomPedidoOutput(
                0,
                aCupomPedido.size(),
                1,
                list);
    }
}
