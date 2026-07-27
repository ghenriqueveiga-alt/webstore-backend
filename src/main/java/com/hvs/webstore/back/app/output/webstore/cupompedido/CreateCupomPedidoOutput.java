package com.hvs.webstore.back.app.output.webstore.cupompedido;

import com.hvs.webstore.back.domain.entity.webstore.cupompedido.CupomPedido;

public record CreateCupomPedidoOutput(Long aId,
                                      String aUuid,
                                      String aMessage) {

    public static CreateCupomPedidoOutput from(CupomPedido aCupomPedido) {

        return new CreateCupomPedidoOutput(
                aCupomPedido.getId().getValue(),
                aCupomPedido.getUuid().getValue(),
                "CupomPedido created: " + aCupomPedido.getUuid().getValue());
    }
}
