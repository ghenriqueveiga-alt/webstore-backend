package com.hvs.webstore.back.app.command.webstore.cupompedido;

public record CreateCupomPedidoCommand(Long aCupomId,
                                       Long aPedidoId,
                                       Long aValorDesconto) {

    public static CreateCupomPedidoCommand from(final Long aCupomId,
                                                final Long aPedidoId,
                                                final Long aValorDesconto) {

        return new CreateCupomPedidoCommand(
                aCupomId,
                aPedidoId,
                aValorDesconto);
    }
}
