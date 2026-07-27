package com.hvs.webstore.back.app.command.webstore.cupompedido;

public record ReadCupomPedidoCommand(Long aId,
                                     String aUuid) {

    public static ReadCupomPedidoCommand from(final Long aId) {

        return new ReadCupomPedidoCommand(
                aId,
                null
        );
    }

    public static ReadCupomPedidoCommand from(final String aUuid) {

        return new ReadCupomPedidoCommand(
                null,
                aUuid
        );
    }
}
