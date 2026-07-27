package com.hvs.webstore.back.domain.entity.television.arquivo;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class ArquivoUuid extends Identifier {

    private final String value;

    private ArquivoUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static ArquivoUuid unique() {

        return new ArquivoUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static ArquivoUuid from(final String aId) {

        return new ArquivoUuid(aId);

    }

    public static ArquivoUuid from(final UUID aId) {

        return new ArquivoUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        ArquivoUuid arquivoUuid = (ArquivoUuid) o;

        return Objects.equals(value, arquivoUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}