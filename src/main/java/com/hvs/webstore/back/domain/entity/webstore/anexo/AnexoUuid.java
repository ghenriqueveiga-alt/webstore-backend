package com.hvs.webstore.back.domain.entity.webstore.anexo;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class AnexoUuid extends Identifier {

    private final String value;

    private AnexoUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static AnexoUuid unique() {

        return new AnexoUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static AnexoUuid from(final String aId) {

        return new AnexoUuid(aId);

    }

    public static AnexoUuid from(final UUID aId) {

        return new AnexoUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        AnexoUuid anexoUuid = (AnexoUuid) o;

        return Objects.equals(value, anexoUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}