package com.hvs.webstore.back.app.command.webstore.pedido;

public record CreateItemPedidoCommand(Long aProdutoId,
                                      Integer aQuantidade,
                                      Long aPrecoId) {

    public static CreateItemPedidoCommand from(final Long aProdutoId,
                                               final Integer aQuantidade,
                                               final Long aPrecoId) {

        return new CreateItemPedidoCommand(
                aProdutoId,
                aQuantidade,
                aPrecoId);
    }
}
