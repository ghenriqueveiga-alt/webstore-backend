package com.hvs.webstore.back.app.output.webstore.pedido;

import com.hvs.webstore.back.domain.entity.webstore.pedido.Pedido;

public record UpdatePedidoOutput(Long aId,
                                 String aUuid,
                                 String aMessage) {

    public static UpdatePedidoOutput from(Pedido aPedido) {

        return new UpdatePedidoOutput(
                aPedido.getId().getValue(),
                aPedido.getUuid().getValue(),
                "The Pedido with id: " + aPedido.getUuid().getValue() + " has been successfully updated.");
    }
}
