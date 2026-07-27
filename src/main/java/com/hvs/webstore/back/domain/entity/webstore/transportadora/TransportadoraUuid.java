package com.hvs.webstore.back.domain.entity.webstore.transportadora;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class TransportadoraUuid extends Identifier {

    private final String value;

    private TransportadoraUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static TransportadoraUuid unique() {

        return new TransportadoraUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static TransportadoraUuid from(final String aId) {

        return new TransportadoraUuid(aId);

    }

    public static TransportadoraUuid from(final UUID aId) {

        return new TransportadoraUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        TransportadoraUuid transportadoraUuid = (TransportadoraUuid) o;

        return Objects.equals(value, transportadoraUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}