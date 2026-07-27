package com.hvs.webstore.back.domain.entity.webstore.pedido;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.webstore.preco.Preco;
import com.hvs.webstore.back.domain.entity.webstore.produto.Produto;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.util.Objects;

public class ItemPedido extends Entity<ItemPedidoId> {

    private final ItemPedidoUuid uuid;
    private final ItemPedidoStatus statusCode;
    private final Pedido pedido;
    private final Produto produto;
    private final Integer quantidade;
    private final Preco preco;
    private final Long subtotal;

    private ItemPedido(final ItemPedidoId id,
                       final ItemPedidoUuid uuid,
                       final ItemPedidoStatus statusCode,
                       final Pedido pedido,
                       final Produto produto,
                       final Integer quantidade,
                       final Preco preco,
                       final Long subtotal) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = preco;
        this.subtotal = subtotal;
    }

    public static ItemPedido create(final Long aPedidoId,
                                    final Long aProdutoId,
                                    final Integer aQuantidade,
                                    final Long aPrecoId) {

        final var id = ItemPedidoId.from(-1L);
        final var uuid = ItemPedidoUuid.unique();
        final var status = ItemPedidoStatus.ACTIVE;
        final var pedido = aPedidoId != null ? Pedido.from(aPedidoId) : null;
        final var produto = aProdutoId != null ? Produto.from(aProdutoId) : null;
        final var preco = aPrecoId != null ? Preco.from(aPrecoId) : null;

        return new ItemPedido(
                id,
                uuid,
                status,
                pedido,
                produto,
                aQuantidade,
                preco,
                0L);
    }

    public static ItemPedido createItemPedido(final Long aPedidoId,
                                              final Long aProdutoId,
                                              final Integer aQuantidade,
                                              final Long aPrecoId) {

        final var id = ItemPedidoId.from(-1L);
        final var uuid = ItemPedidoUuid.unique();
        final var status = ItemPedidoStatus.ACTIVE;
        final var pedido = aPedidoId != null ? Pedido.from(aPedidoId) : null;
        final var produto = aProdutoId != null ? Produto.from(aProdutoId) : null;
        final var preco = aPrecoId != null ? Preco.from(aPrecoId) : null;

        return new ItemPedido(
                id,
                uuid,
                status,
                pedido,
                produto,
                aQuantidade,
                preco,
                0L);
    }

    public static ItemPedido from(final Long aId,
                                  final String aUuid,
                                  final String aStatusCode,
                                  final Pedido aPedido,
                                  final Produto aProduto,
                                  final Integer aQuantidade,
                                  final Preco aPreco,
                                  final Long aSubtotal) {

        final var id = aId != null ? ItemPedidoId.from(aId) : null;
        final var uuid = aUuid != null ? ItemPedidoUuid.from(aUuid) : null;
        final var status = aStatusCode != null ? ItemPedidoStatus.findByCode(aStatusCode) : null;

        return new ItemPedido(
                id,
                uuid,
                status,
                aPedido,
                aProduto,
                aQuantidade,
                aPreco,
                aSubtotal);
    }

    public static ItemPedido from(final Long aId) {

        final var id = aId != null ? ItemPedidoId.from(aId) : null;

        return new ItemPedido(
                id,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public static ItemPedido from(final String aUuid) {

        final var uuid = aUuid != null ? ItemPedidoUuid.from(aUuid) : null;

        return new ItemPedido(
                null,
                uuid,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public ItemPedido withPreco(final Preco aPreco) {

        return new ItemPedido(
                id,
                uuid,
                statusCode,
                pedido,
                produto,
                quantidade,
                aPreco,
                subtotal);
    }

    public ItemPedido withSubtotal(final Long aSubtotal) {

        return new ItemPedido(
                id,
                uuid,
                statusCode,
                pedido,
                produto,
                quantidade,
                preco,
                aSubtotal);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

    }

    public ItemPedidoUuid getUuid() {
        return uuid;
    }
    public ItemPedidoStatus getStatusCode() {
        return statusCode;
    }
    public Pedido getPedido() {
        return pedido;
    }
    public Produto getProduto() {
        return produto;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public Preco getPreco() {
        return preco;
    }
    public Long getSubtotal() {
        return subtotal;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        ItemPedido itemPedido = (ItemPedido) o;

        return Objects.equals(uuid, itemPedido.uuid) &&
                statusCode == itemPedido.statusCode &&
                Objects.equals(pedido, itemPedido.pedido) &&
                Objects.equals(produto, itemPedido.produto) &&
                Objects.equals(quantidade, itemPedido.quantidade) &&
                Objects.equals(preco, itemPedido.preco) &&
                Objects.equals(subtotal, itemPedido.subtotal);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                pedido,
                produto,
                quantidade,
                preco,
                subtotal);
    }
}
