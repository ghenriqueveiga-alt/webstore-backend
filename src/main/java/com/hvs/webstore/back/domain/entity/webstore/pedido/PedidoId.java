package com.hvs.webstore.back.domain.entity.webstore.pedido;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class PedidoId extends Identifier {

    private final Long value;

    private PedidoId(final Long value) {

        this.value = value;
    }

    public static PedidoId from(final Long aId) {

        return new PedidoId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        PedidoId pedidoId = (PedidoId) o;

        return Objects.equals(value, pedidoId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}