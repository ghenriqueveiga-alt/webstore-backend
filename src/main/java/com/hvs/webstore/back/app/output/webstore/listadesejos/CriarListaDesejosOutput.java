package com.hvs.webstore.back.app.output.webstore.listadesejos;

import com.hvs.webstore.back.domain.entity.webstore.listadesejos.ListaDesejos;

public record CriarListaDesejosOutput(Long aId,
                                      String aUuid,
                                      String aMessage) {

    public static CriarListaDesejosOutput from(ListaDesejos aListaDesejos) {

        return new CriarListaDesejosOutput(
                aListaDesejos.getId().getValue(),
                aListaDesejos.getUuid().getValue(),
                "The ListaDesejos with id: " + aListaDesejos.getUuid().getValue() + " has been successfully created.");
    }
}
