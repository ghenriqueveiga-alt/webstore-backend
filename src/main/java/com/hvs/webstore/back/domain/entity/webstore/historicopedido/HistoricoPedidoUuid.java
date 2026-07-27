package com.hvs.webstore.back.domain.entity.webstore.historicopedido;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class HistoricoPedidoUuid extends Identifier {

    private final String value;

    private HistoricoPedidoUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static HistoricoPedidoUuid unique() {

        return new HistoricoPedidoUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static HistoricoPedidoUuid from(final String aId) {

        return new HistoricoPedidoUuid(aId);

    }

    public static HistoricoPedidoUuid from(final UUID aId) {

        return new HistoricoPedidoUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        HistoricoPedidoUuid historicoPedidoUuid = (HistoricoPedidoUuid) o;

        return Objects.equals(value, historicoPedidoUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}