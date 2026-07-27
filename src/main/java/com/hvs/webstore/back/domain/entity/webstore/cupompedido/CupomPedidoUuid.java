package com.hvs.webstore.back.domain.entity.webstore.cupompedido;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class CupomPedidoUuid extends Identifier {

    private final String value;

    private CupomPedidoUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static CupomPedidoUuid unique() {

        return new CupomPedidoUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static CupomPedidoUuid from(final String aId) {

        return new CupomPedidoUuid(aId);

    }

    public static CupomPedidoUuid from(final UUID aId) {

        return new CupomPedidoUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        CupomPedidoUuid cupomPedidoUuid = (CupomPedidoUuid) o;

        return Objects.equals(value, cupomPedidoUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}