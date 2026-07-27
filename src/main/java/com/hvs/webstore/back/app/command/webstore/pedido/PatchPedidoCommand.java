package com.hvs.webstore.back.app.command.webstore.pedido;

import java.time.Instant;
import java.util.List;

public record PatchPedidoCommand(Long aId,
                                 String aUuid,
                                 String aStatusCode,
                                 Long aEnderecoEntregaId,
                                 Long aFormaPagamentoId,
                                 Instant aDataPagamento,
                                 Instant aDataEnvio,
                                 Long aTotal,
                                 List<CreateItemPedidoCommand> aItems) {

    public static PatchPedidoCommand from(final Long aId,
                                          final PatchPedidoCommand aInput) {

        return new PatchPedidoCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aEnderecoEntregaId,
                aInput.aFormaPagamentoId,
                aInput.aDataPagamento,
                aInput.aDataEnvio,
                aInput.aTotal,
                aInput.aItems);
    }

    public static PatchPedidoCommand from(final String aUuid,
                                          final PatchPedidoCommand aInput) {

        return new PatchPedidoCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aEnderecoEntregaId,
                aInput.aFormaPagamentoId,
                aInput.aDataPagamento,
                aInput.aDataEnvio,
                aInput.aTotal,
                aInput.aItems);
    }
}
