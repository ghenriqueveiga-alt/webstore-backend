package com.hvs.webstore.back.domain.entity.television.corte;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class CorteUuid extends Identifier {

    private final String value;

    private CorteUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static CorteUuid unique() {

        return new CorteUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static CorteUuid from(final String aId) {

        return new CorteUuid(aId);

    }

    public static CorteUuid from(final UUID aId) {

        return new CorteUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        CorteUuid corteUuid = (CorteUuid) o;

        return Objects.equals(value, corteUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}