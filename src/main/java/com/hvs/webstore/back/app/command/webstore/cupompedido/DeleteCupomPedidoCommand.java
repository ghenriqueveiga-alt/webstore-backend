package com.hvs.webstore.back.app.command.webstore.cupompedido;

public record DeleteCupomPedidoCommand(Long aId,
                                       String aUuid) {

    public static DeleteCupomPedidoCommand from(final Long aId) {

        return new DeleteCupomPedidoCommand(
                aId,
                null);
    }

    public static DeleteCupomPedidoCommand from(final String aUuid) {

        return new DeleteCupomPedidoCommand(
                null,
                aUuid);
    }
}
