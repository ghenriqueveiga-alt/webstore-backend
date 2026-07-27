package com.hvs.webstore.back.app.output.webstore.listadesejos;

import com.hvs.webstore.back.domain.entity.webstore.listadesejos.ItemListaDesejos;
import com.hvs.webstore.back.domain.entity.webstore.listadesejos.ListaDesejos;

public record AdicionarItemListaOutput(Long aListaId,
                                       String aListaUuid,
                                       String aItemUuid,
                                       String aMessage) {

    public static AdicionarItemListaOutput from(ListaDesejos aListaDesejos, ItemListaDesejos aItemListaDesejos) {

        return new AdicionarItemListaOutput(
                aListaDesejos.getId().getValue(),
                aListaDesejos.getUuid().getValue(),
                aItemListaDesejos.getUuid().getValue(),
                "The item with uuid: " + aItemListaDesejos.getUuid().getValue() + " has been successfully added to the wishlist.");
    }
}
