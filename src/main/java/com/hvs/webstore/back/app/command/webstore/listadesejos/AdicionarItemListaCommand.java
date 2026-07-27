package com.hvs.webstore.back.app.command.webstore.listadesejos;

public record AdicionarItemListaCommand(String listaUuid,
                                        Long produtoId) {

    public static AdicionarItemListaCommand from(final String listaUuid,
                                                 final Long produtoId) {

        return new AdicionarItemListaCommand(
                listaUuid,
                produtoId);
    }
}
