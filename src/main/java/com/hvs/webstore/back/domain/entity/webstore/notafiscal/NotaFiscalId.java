package com.hvs.webstore.back.domain.entity.webstore.notafiscal;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class NotaFiscalId extends Identifier {

    private final Long value;

    private NotaFiscalId(final Long value) {

        this.value = value;
    }

    public static NotaFiscalId from(final Long aId) {

        return new NotaFiscalId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        NotaFiscalId notaFiscalId = (NotaFiscalId) o;

        return Objects.equals(value, notaFiscalId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}