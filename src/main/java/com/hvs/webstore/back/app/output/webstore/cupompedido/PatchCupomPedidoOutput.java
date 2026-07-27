package com.hvs.webstore.back.app.output.webstore.cupompedido;

import com.hvs.webstore.back.domain.entity.webstore.cupompedido.CupomPedido;

public record PatchCupomPedidoOutput(Long aId,
                                     String aUuid,
                                     String aMessage) {

    public static PatchCupomPedidoOutput from(CupomPedido aCupomPedido) {

        return new PatchCupomPedidoOutput(
                aCupomPedido.getId().getValue(),
                aCupomPedido.getUuid().getValue(),
                "CupomPedido patched: " + aCupomPedido.getUuid().getValue());
    }
}
