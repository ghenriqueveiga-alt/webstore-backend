package com.hvs.webstore.back.app.command.webstore.cupompedido;

public record UpdateCupomPedidoCommand(Long aId,
                                       String aUuid,
                                       String aStatusCode,
                                       Long aCupomId,
                                       Long aPedidoId,
                                       Long aValorDesconto) {

    public static UpdateCupomPedidoCommand from(final Long aId,
                                                final UpdateCupomPedidoCommand aInput) {

        return new UpdateCupomPedidoCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aCupomId,
                aInput.aPedidoId,
                aInput.aValorDesconto
        );
    }

    public static UpdateCupomPedidoCommand from(final String aUuid,
                                                final UpdateCupomPedidoCommand aInput) {

        return new UpdateCupomPedidoCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aCupomId,
                aInput.aPedidoId,
                aInput.aValorDesconto
        );
    }
}
