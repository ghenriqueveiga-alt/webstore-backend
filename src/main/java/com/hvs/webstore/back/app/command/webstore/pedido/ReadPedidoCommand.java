package com.hvs.webstore.back.app.command.webstore.pedido;

public record ReadPedidoCommand(Long aId,
                                String aUuid) {

    public static ReadPedidoCommand from(final Long aId) {

        return new ReadPedidoCommand(
                aId,
                null
        );
    }

    public static ReadPedidoCommand from(final String aUuid) {

        return new ReadPedidoCommand(
                null,
                aUuid
        );
    }
}
