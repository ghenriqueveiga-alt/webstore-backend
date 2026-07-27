package com.hvs.webstore.back.app.output.webstore.listadesejos;

import com.hvs.webstore.back.domain.entity.webstore.listadesejos.ListaDesejos;

public record RemoverItemListaOutput(Long aListaId,
                                     String aListaUuid,
                                     String aMessage) {

    public static RemoverItemListaOutput from(ListaDesejos aListaDesejos) {

        return new RemoverItemListaOutput(
                aListaDesejos.getId().getValue(),
                aListaDesejos.getUuid().getValue(),
                "The item has been successfully removed from the wishlist.");
    }
}
