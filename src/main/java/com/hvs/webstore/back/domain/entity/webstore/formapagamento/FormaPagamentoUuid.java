package com.hvs.webstore.back.domain.entity.webstore.formapagamento;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class FormaPagamentoUuid extends Identifier {

    private final String value;

    private FormaPagamentoUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static FormaPagamentoUuid unique() {

        return new FormaPagamentoUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static FormaPagamentoUuid from(final String aId) {

        return new FormaPagamentoUuid(aId);

    }

    public static FormaPagamentoUuid from(final UUID aId) {

        return new FormaPagamentoUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        FormaPagamentoUuid formaPagamentoUuid = (FormaPagamentoUuid) o;

        return Objects.equals(value, formaPagamentoUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}