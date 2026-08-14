package com.hvs.webstore.back.app.output.webstore.pedido;

import com.hvs.webstore.back.domain.entity.webstore.pedido.Pedido;
import com.hvs.webstore.back.domain.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

public record ReadAllPedidoOutput(int aCurrentPage,
                                  long aPerPage,
                                  long aTotal,
                                  List<ReadPedidoOutput> aOrdens) {

    public static ReadAllPedidoOutput from(Pagination<Pedido> aPagination) {

        final List<ReadPedidoOutput> list = new ArrayList<>();

        for (Pedido aPedido : aPagination.aContent())
            list.add(ReadPedidoOutput.from(aPedido));

        return new ReadAllPedidoOutput(
                aPagination.aPageNumber(),
                aPagination.aContent().size(),
                aPagination.aTotalElements(),
                list);
    }
}
