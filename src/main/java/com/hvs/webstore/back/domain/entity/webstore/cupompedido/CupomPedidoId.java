package com.hvs.webstore.back.domain.entity.webstore.cupompedido;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class CupomPedidoId extends Identifier {

    private final Long value;

    private CupomPedidoId(final Long value) {

        this.value = value;
    }

    public static CupomPedidoId from(final Long aId) {

        return new CupomPedidoId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        CupomPedidoId cupomPedidoId = (CupomPedidoId) o;

        return Objects.equals(value, cupomPedidoId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}