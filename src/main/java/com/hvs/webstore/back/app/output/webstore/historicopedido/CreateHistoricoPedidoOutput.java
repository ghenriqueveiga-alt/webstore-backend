package com.hvs.webstore.back.app.output.webstore.historicopedido;

import com.hvs.webstore.back.domain.entity.webstore.historicopedido.HistoricoPedido;

public record CreateHistoricoPedidoOutput(Long aId,
                                          String aUuid,
                                          String aMessage) {

    public static CreateHistoricoPedidoOutput from(HistoricoPedido aHistoricoPedido) {

        return new CreateHistoricoPedidoOutput(
                aHistoricoPedido.getId().getValue(),
                aHistoricoPedido.getUuid().getValue(),
                "HistoricoPedido created: " + aHistoricoPedido.getUuid().getValue());
    }
}
