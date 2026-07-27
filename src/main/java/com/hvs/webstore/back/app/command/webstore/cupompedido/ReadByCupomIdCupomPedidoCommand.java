package com.hvs.webstore.back.app.command.webstore.cupompedido;

public record ReadByCupomIdCupomPedidoCommand(Long aCupomId) {

    public static ReadByCupomIdCupomPedidoCommand from(final Long aCupomId) {

        return new ReadByCupomIdCupomPedidoCommand(aCupomId);
    }
}
