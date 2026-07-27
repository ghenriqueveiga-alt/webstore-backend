package com.hvs.webstore.back.domain.entity.webstore.marca;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class MarcaUuid extends Identifier {

    private final String value;

    private MarcaUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static MarcaUuid unique() {

        return new MarcaUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static MarcaUuid from(final String aId) {

        return new MarcaUuid(aId);

    }

    public static MarcaUuid from(final UUID aId) {

        return new MarcaUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        MarcaUuid marcaUuid = (MarcaUuid) o;

        return Objects.equals(value, marcaUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}