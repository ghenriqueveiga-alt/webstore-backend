package com.hvs.webstore.back.app.output.webstore.pedido;

import com.hvs.webstore.back.domain.entity.webstore.pedido.Pedido;

import java.time.Instant;
import java.util.List;

public record ReadPedidoOutput(Long aId,
                               String aUuid,
                               String aStatusDesc,
                               Long aUsuarioId,
                               Long aEnderecoEntregaId,
                               Long aFormaPagamentoId,
                               Instant aDataCriacao,
                               Instant aDataPagamento,
                               Instant aDataEnvio,
                               Long aTotal,
                               List<ReadItemPedidoOutput> aItems) {

    public static ReadPedidoOutput from(Pedido aPedido) {

        return new ReadPedidoOutput(
                aPedido.getId().getValue(),
                aPedido.getUuid().getValue(),
                aPedido.getStatusCode().getDesc(),
                aPedido.getUsuario() != null ? aPedido.getUsuario().getId().getValue() : null,
                aPedido.getEnderecoEntrega() != null ? aPedido.getEnderecoEntrega().getId().getValue() : null,
                aPedido.getFormaPagamento() != null ? aPedido.getFormaPagamento().getId().getValue() : null,
                aPedido.getDataCriacao(),
                aPedido.getDataPagamento(),
                aPedido.getDataEnvio(),
                aPedido.getTotal(),
                aPedido.getItems() != null ?
                        aPedido.getItems().stream().map(ReadItemPedidoOutput::from).toList() : null);
    }

    public static ReadPedidoOutput fromSimple(Pedido aPedido) {

        return new ReadPedidoOutput(
                aPedido.getId().getValue(),
                aPedido.getUuid().getValue(),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }
}
