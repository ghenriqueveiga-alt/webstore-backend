package com.hvs.webstore.back.app.output.webstore.pedido;

import com.hvs.webstore.back.domain.entity.webstore.pedido.ItemPedido;

public record ReadItemPedidoOutput(Long aId,
                                   String aUuid,
                                   String aStatusDesc,
                                   Long aPedidoId,
                                   Long aProdutoId,
                                   Integer aQuantidade,
                                   Long aPrecoId,
                                   Long aSubtotal) {

    public static ReadItemPedidoOutput from(ItemPedido aItemPedido) {

        return new ReadItemPedidoOutput(
                aItemPedido.getId().getValue(),
                aItemPedido.getUuid().getValue(),
                aItemPedido.getStatusCode().getDesc(),
                aItemPedido.getPedido() != null ? aItemPedido.getPedido().getId().getValue() : null,
                aItemPedido.getProduto() != null ? aItemPedido.getProduto().getId().getValue() : null,
                aItemPedido.getQuantidade(),
                aItemPedido.getPreco() != null ? aItemPedido.getPreco().getId().getValue() : null,
                aItemPedido.getSubtotal());
    }

    public static ReadItemPedidoOutput fromSimple(ItemPedido aItemPedido) {

        return new ReadItemPedidoOutput(
                aItemPedido.getId().getValue(),
                aItemPedido.getUuid().getValue(),
                null,
                null,
                null,
                null,
                null,
                null);
    }
}
