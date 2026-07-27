package com.hvs.webstore.back.domain.entity.webstore.formapagamento;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class FormaPagamentoId extends Identifier {

    private final Long value;

    private FormaPagamentoId(final Long value) {

        this.value = value;
    }

    public static FormaPagamentoId from(final Long aId) {

        return new FormaPagamentoId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        FormaPagamentoId formaPagamentoId = (FormaPagamentoId) o;

        return Objects.equals(value, formaPagamentoId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}