package com.hvs.webstore.back.app.output.webstore.cupompedido;

import com.hvs.webstore.back.domain.entity.webstore.cupompedido.CupomPedido;

public record ReadCupomPedidoOutput(Long aId,
                                    String aUuid,
                                    String aStatusDesc,
                                    Long aCupomId,
                                    Long aPedidoId,
                                    Long aValorDesconto) {

    public static ReadCupomPedidoOutput from(CupomPedido aCupomPedido) {

        return new ReadCupomPedidoOutput(
                aCupomPedido.getId().getValue(),
                aCupomPedido.getUuid().getValue(),
                aCupomPedido.getStatusCode().getDesc(),
                aCupomPedido.getCupom().getId().getValue(),
                aCupomPedido.getPedido().getId().getValue(),
                aCupomPedido.getValorDesconto().getId().getValue());
    }

    public static ReadCupomPedidoOutput fromSimple(CupomPedido aCupomPedido) {

        return new ReadCupomPedidoOutput(
                aCupomPedido.getId().getValue(),
                aCupomPedido.getUuid().getValue(),
                null,
                null,
                null,
                null);
    }
}
