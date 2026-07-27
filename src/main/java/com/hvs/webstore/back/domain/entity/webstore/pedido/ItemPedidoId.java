package com.hvs.webstore.back.domain.entity.webstore.pedido;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class ItemPedidoId extends Identifier {

    private final Long value;

    private ItemPedidoId(final Long value) {

        this.value = value;
    }

    public static ItemPedidoId from(final Long aId) {

        return new ItemPedidoId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        ItemPedidoId itemPedidoId = (ItemPedidoId) o;

        return Objects.equals(value, itemPedidoId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}