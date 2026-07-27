package com.hvs.webstore.back.app.command.webstore.pedido;

public record DeletePedidoCommand(Long aId,
                                  String aUuid) {

    public static DeletePedidoCommand from(final Long aId) {

        return new DeletePedidoCommand(
                aId,
                null);
    }

    public static DeletePedidoCommand from(final String aUuid) {

        return new DeletePedidoCommand(
                null,
                aUuid);
    }
}
