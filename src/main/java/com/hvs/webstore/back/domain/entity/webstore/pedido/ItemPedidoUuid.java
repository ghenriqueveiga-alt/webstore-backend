package com.hvs.webstore.back.domain.entity.webstore.pedido;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class ItemPedidoUuid extends Identifier {

    private final String value;

    private ItemPedidoUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static ItemPedidoUuid unique() {

        return new ItemPedidoUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static ItemPedidoUuid from(final String aId) {

        return new ItemPedidoUuid(aId);

    }

    public static ItemPedidoUuid from(final UUID aId) {

        return new ItemPedidoUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        ItemPedidoUuid itemPedidoUuid = (ItemPedidoUuid) o;

        return Objects.equals(value, itemPedidoUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}