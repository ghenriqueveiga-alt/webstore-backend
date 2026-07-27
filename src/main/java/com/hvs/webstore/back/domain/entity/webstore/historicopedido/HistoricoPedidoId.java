package com.hvs.webstore.back.domain.entity.webstore.historicopedido;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class HistoricoPedidoId extends Identifier {

    private final Long value;

    private HistoricoPedidoId(final Long value) {

        this.value = value;
    }

    public static HistoricoPedidoId from(final Long aId) {

        return new HistoricoPedidoId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        HistoricoPedidoId historicoPedidoId = (HistoricoPedidoId) o;

        return Objects.equals(value, historicoPedidoId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}