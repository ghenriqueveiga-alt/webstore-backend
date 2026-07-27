package com.hvs.webstore.back.app.command.webstore.pedido;

import java.time.Instant;
import java.util.List;

public record UpdatePedidoCommand(Long aId,
                                  String aUuid,
                                  String aStatusCode,
                                  Long aUsuarioId,
                                  Long aEnderecoEntregaId,
                                  Long aFormaPagamentoId,
                                  Instant aDataCriacao,
                                  Instant aDataPagamento,
                                  Instant aDataEnvio,
                                  Long aTotal,
                                  List<CreateItemPedidoCommand> aItems) {

    public static UpdatePedidoCommand from(final Long aId,
                                           final UpdatePedidoCommand aInput) {

        return new UpdatePedidoCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aUsuarioId,
                aInput.aEnderecoEntregaId,
                aInput.aFormaPagamentoId,
                aInput.aDataCriacao,
                aInput.aDataPagamento,
                aInput.aDataEnvio,
                aInput.aTotal,
                aInput.aItems
        );
    }

    public static UpdatePedidoCommand from(final String aUuid,
                                           final UpdatePedidoCommand aInput) {

        return new UpdatePedidoCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aUsuarioId,
                aInput.aEnderecoEntregaId,
                aInput.aFormaPagamentoId,
                aInput.aDataCriacao,
                aInput.aDataPagamento,
                aInput.aDataEnvio,
                aInput.aTotal,
                aInput.aItems
        );
    }
}
