package com.hvs.webstore.back.domain.entity.webstore.frete;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class FreteUuid extends Identifier {

    private final String value;

    private FreteUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static FreteUuid unique() {

        return new FreteUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static FreteUuid from(final String aId) {

        return new FreteUuid(aId);

    }

    public static FreteUuid from(final UUID aId) {

        return new FreteUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        FreteUuid freteUuid = (FreteUuid) o;

        return Objects.equals(value, freteUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}