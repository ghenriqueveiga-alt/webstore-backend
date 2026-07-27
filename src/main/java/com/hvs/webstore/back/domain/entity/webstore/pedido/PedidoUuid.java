package com.hvs.webstore.back.domain.entity.webstore.pedido;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class PedidoUuid extends Identifier {

    private final String value;

    private PedidoUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static PedidoUuid unique() {

        return new PedidoUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static PedidoUuid from(final String aId) {

        return new PedidoUuid(aId);

    }

    public static PedidoUuid from(final UUID aId) {

        return new PedidoUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        PedidoUuid pedidoUuid = (PedidoUuid) o;

        return Objects.equals(value, pedidoUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}