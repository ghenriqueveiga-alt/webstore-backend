package com.hvs.webstore.back.app.output.webstore.cupompedido;

import com.hvs.webstore.back.domain.entity.webstore.cupompedido.CupomPedido;

public record DeleteCupomPedidoOutput(Long aId,
                                      String aUuid,
                                      String aMessage) {

    public static DeleteCupomPedidoOutput from(CupomPedido aCupomPedido) {

        return new DeleteCupomPedidoOutput(
                aCupomPedido.getId().getValue(),
                aCupomPedido.getUuid().getValue(),
                "CupomPedido deleted: " + aCupomPedido.getUuid().getValue());
    }
}
