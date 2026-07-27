package com.hvs.webstore.back.app.command.webstore.cupompedido;

public record PatchCupomPedidoCommand(Long aId,
                                      String aUuid,
                                      String aStatusCode,
                                      Long aCupomId,
                                      Long aPedidoId,
                                      Long aValorDesconto) {

    public static PatchCupomPedidoCommand from(final Long aId,
                                               final PatchCupomPedidoCommand aIn) {

        return new PatchCupomPedidoCommand(
                aId,
                null,
                aIn.aStatusCode,
                aIn.aCupomId,
                aIn.aPedidoId,
                aIn.aValorDesconto);
    }

    public static PatchCupomPedidoCommand from(final String aUuid,
                                               final PatchCupomPedidoCommand aIn) {

        return new PatchCupomPedidoCommand(
                null,
                aUuid,
                aIn.aStatusCode,
                aIn.aCupomId,
                aIn.aPedidoId,
                aIn.aValorDesconto);
    }
}
