package com.hvs.webstore.back.domain.entity.webstore.pagamento;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class PagamentoUuid extends Identifier {

    private final String value;

    private PagamentoUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static PagamentoUuid unique() {

        return new PagamentoUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static PagamentoUuid from(final String aId) {

        return new PagamentoUuid(aId);

    }

    public static PagamentoUuid from(final UUID aId) {

        return new PagamentoUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        PagamentoUuid pagamentoUuid = (PagamentoUuid) o;

        return Objects.equals(value, pagamentoUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}