package com.hvs.webstore.back.app.output.webstore.historicopedido;

import com.hvs.webstore.back.domain.entity.webstore.historicopedido.HistoricoPedido;
import com.hvs.webstore.back.domain.pagination.Pagination;
import java.util.ArrayList;
import java.util.List;

public record ReadAllHistoricoPedidoOutput(int aCurrentPage,
                                           long aPerPage,
                                           long aTotal,
                                           java.util.List<ReadHistoricoPedidoOutput> aHistorico) {

    public static ReadAllHistoricoPedidoOutput from(Pagination<HistoricoPedido> aPagination) {

        final List<ReadHistoricoPedidoOutput> list = new ArrayList<>();

        for (var i : aPagination.aContent())
            list.add(ReadHistoricoPedidoOutput.from(i));

        return new ReadAllHistoricoPedidoOutput(
                aPagination.aPageNumber(),
                aPagination.aContent().size(),
                aPagination.aTotalElements(),
                list);
    }

    public static ReadAllHistoricoPedidoOutput from(java.util.List<HistoricoPedido> aList) {

        final List<ReadHistoricoPedidoOutput> list = new ArrayList<>();

        for (var i : aList)
            list.add(ReadHistoricoPedidoOutput.from(i));

        return new ReadAllHistoricoPedidoOutput(
                0,
                aList.size(),
                1,
                list);
    }
}
