package com.hvs.webstore.back.app.command.webstore.historicopedido;

public record ReadHistoricoPedidoByPedidoIdCommand(Long aPedidoId) {

    public static ReadHistoricoPedidoByPedidoIdCommand from(final Long aPedidoId) {

        return new ReadHistoricoPedidoByPedidoIdCommand(aPedidoId);
    }
}
