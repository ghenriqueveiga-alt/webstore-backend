package com.hvs.webstore.back.app.command.webstore.historicopedido;

public record ReadHistoricoPedidoCommand(Long aId,
                                         String aUuid) {

    public static ReadHistoricoPedidoCommand from(final Long aId) {

        return new ReadHistoricoPedidoCommand(
                aId,
                null
        );
    }

    public static ReadHistoricoPedidoCommand from(final String aUuid) {

        return new ReadHistoricoPedidoCommand(
                null,
                aUuid
        );
    }
}
