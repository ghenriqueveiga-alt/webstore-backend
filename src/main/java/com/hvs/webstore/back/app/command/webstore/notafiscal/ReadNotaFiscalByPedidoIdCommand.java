package com.hvs.webstore.back.app.command.webstore.notafiscal;

public record ReadNotaFiscalByPedidoIdCommand(Long aPedidoId) {

    public static ReadNotaFiscalByPedidoIdCommand from(final Long aPedidoId) {

        return new ReadNotaFiscalByPedidoIdCommand(aPedidoId);
    }
}
