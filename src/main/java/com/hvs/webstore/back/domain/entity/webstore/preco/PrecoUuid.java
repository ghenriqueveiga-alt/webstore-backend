package com.hvs.webstore.back.domain.entity.webstore.preco;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class PrecoUuid extends Identifier {

    private final String value;

    private PrecoUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static PrecoUuid unique() {

        return new PrecoUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static PrecoUuid from(final String aId) {

        return new PrecoUuid(aId);

    }

    public static PrecoUuid from(final UUID aId) {

        return new PrecoUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        PrecoUuid precoUuid = (PrecoUuid) o;

        return Objects.equals(value, precoUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}