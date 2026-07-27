package com.hvs.webstore.back.app.command.webstore.cupompedido;

public record ReadByPedidoIdCupomPedidoCommand(Long aPedidoId) {

    public static ReadByPedidoIdCupomPedidoCommand from(final Long aPedidoId) {

        return new ReadByPedidoIdCupomPedidoCommand(aPedidoId);
    }
}
