package com.hvs.webstore.back.app.output.webstore.cupompedido;

import com.hvs.webstore.back.domain.entity.webstore.cupompedido.CupomPedido;

public record UpdateCupomPedidoOutput(Long aId,
                                      String aUuid,
                                      String aMessage) {

    public static UpdateCupomPedidoOutput from(CupomPedido aCupomPedido) {

        return new UpdateCupomPedidoOutput(
                aCupomPedido.getId().getValue(),
                aCupomPedido.getUuid().getValue(),
                "CupomPedido updated: " + aCupomPedido.getUuid().getValue());
    }
}
