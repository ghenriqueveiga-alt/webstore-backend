package com.hvs.webstore.back.domain.entity.webstore.pagamento;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class PagamentoId extends Identifier {

    private final Long value;

    private PagamentoId(final Long value) {

        this.value = value;
    }

    public static PagamentoId from(final Long aId) {

        return new PagamentoId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        PagamentoId pagamentoId = (PagamentoId) o;

        return Objects.equals(value, pagamentoId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}