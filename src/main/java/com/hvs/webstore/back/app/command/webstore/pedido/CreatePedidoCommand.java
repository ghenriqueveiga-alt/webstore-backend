package com.hvs.webstore.back.app.command.webstore.pedido;

import java.util.List;

public record CreatePedidoCommand(Long aUsuarioId,
                                  Long aEnderecoEntregaId,
                                  Long aFormaPagamentoId,
                                  List<CreateItemPedidoCommand> aItems) {

    public static CreatePedidoCommand from(final Long aUsuarioId,
                                           final Long aEnderecoEntregaId,
                                           final Long aFormaPagamentoId,
                                           final List<CreateItemPedidoCommand> aItems) {

        return new CreatePedidoCommand(
                aUsuarioId,
                aEnderecoEntregaId,
                aFormaPagamentoId,
                aItems);
    }
}
