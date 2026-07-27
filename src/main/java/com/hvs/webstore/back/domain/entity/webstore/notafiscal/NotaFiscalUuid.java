package com.hvs.webstore.back.domain.entity.webstore.notafiscal;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class NotaFiscalUuid extends Identifier {

    private final String value;

    private NotaFiscalUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static NotaFiscalUuid unique() {

        return new NotaFiscalUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static NotaFiscalUuid from(final String aId) {

        return new NotaFiscalUuid(aId);

    }

    public static NotaFiscalUuid from(final UUID aId) {

        return new NotaFiscalUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        NotaFiscalUuid notaFiscalUuid = (NotaFiscalUuid) o;

        return Objects.equals(value, notaFiscalUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}