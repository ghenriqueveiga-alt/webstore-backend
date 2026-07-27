package com.hvs.webstore.back.app.command.webstore.listadesejos;

public record RemoverItemListaCommand(String listaUuid,
                                      String itemUuid) {

    public static RemoverItemListaCommand from(final String listaUuid,
                                               final String itemUuid) {

        return new RemoverItemListaCommand(
                listaUuid,
                itemUuid);
    }
}
