package com.hvs.webstore.back.app.output.webstore.historicopedido;

import com.hvs.webstore.back.domain.entity.webstore.historicopedido.HistoricoPedido;
import java.time.Instant;

public record ReadHistoricoPedidoOutput(Long aId,
                                        String aUuid,
                                        String aStatusDesc,
                                        Long aPedidoId,
                                        String aStatusAnterior,
                                        String aStatusNovo,
                                        String aObservacao,
                                        String aCriadoPor,
                                        Instant aDataCriacao) {

    public static ReadHistoricoPedidoOutput from(HistoricoPedido aHistoricoPedido) {

        return new ReadHistoricoPedidoOutput(
                aHistoricoPedido.getId().getValue(),
                aHistoricoPedido.getUuid().getValue(),
                aHistoricoPedido.getStatusCode().getDesc(),
                aHistoricoPedido.getPedido() != null ? aHistoricoPedido.getPedido().getId().getValue() : null,
                aHistoricoPedido.getStatusAnterior(),
                aHistoricoPedido.getStatusNovo(),
                aHistoricoPedido.getObservacao(),
                aHistoricoPedido.getCriadoPor(),
                aHistoricoPedido.getDataCriacao());
    }

    public static ReadHistoricoPedidoOutput fromSimple(HistoricoPedido aHistoricoPedido) {

        return new ReadHistoricoPedidoOutput(
                aHistoricoPedido.getId().getValue(),
                aHistoricoPedido.getUuid().getValue(),
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }
}
