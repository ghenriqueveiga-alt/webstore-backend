package com.hvs.webstore.back.app.command.webstore.historicopedido;

public record CreateHistoricoPedidoCommand(Long aPedidoId,
                                           String aStatusAnterior,
                                           String aStatusNovo,
                                           String aObservacao,
                                           String aCriadoPor) {

    public static CreateHistoricoPedidoCommand from(final Long aPedidoId,
                                                    final String aStatusAnterior,
                                                    final String aStatusNovo,
                                                    final String aObservacao,
                                                    final String aCriadoPor) {

        return new CreateHistoricoPedidoCommand(
                aPedidoId,
                aStatusAnterior,
                aStatusNovo,
                aObservacao,
                aCriadoPor);
    }
}
