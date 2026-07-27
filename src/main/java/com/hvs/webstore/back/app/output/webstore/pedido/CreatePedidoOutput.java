package com.hvs.webstore.back.app.output.webstore.pedido;

import com.hvs.webstore.back.domain.entity.webstore.pedido.Pedido;

public record CreatePedidoOutput(Long aId,
                                 String aUuid,
                                 String aMessage) {

    public static CreatePedidoOutput from(Pedido aPedido) {

        return new CreatePedidoOutput(
                aPedido.getId().getValue(),
                aPedido.getUuid().getValue(),
                "The Pedido with id: " + aPedido.getUuid().getValue() + " has been successfully created.");
    }
}
