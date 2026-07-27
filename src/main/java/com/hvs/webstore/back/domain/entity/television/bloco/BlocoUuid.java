package com.hvs.webstore.back.domain.entity.television.bloco;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class BlocoUuid extends Identifier {

    private final String value;

    private BlocoUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static BlocoUuid unique() {

        return new BlocoUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static BlocoUuid from(final String aId) {

        return new BlocoUuid(aId);

    }

    public static BlocoUuid from(final UUID aId) {

        return new BlocoUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        BlocoUuid blocoUuid = (BlocoUuid) o;

        return Objects.equals(value, blocoUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}