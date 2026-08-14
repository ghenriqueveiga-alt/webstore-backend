package com.hvs.webstore.back.domain.entity.television.genero;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class GeneroUuid extends Identifier {

    private final String value;

    private GeneroUuid(final String aValue) {
        this.value = Objects.requireNonNull(aValue);
    }

    public static GeneroUuid unique() {
        return new GeneroUuid(UUID.randomUUID().toString().toLowerCase());
    }

    public static GeneroUuid from(final String aId) {
        return new GeneroUuid(aId);
    }

    public static GeneroUuid from(final UUID aId) {
        return new GeneroUuid(aId.toString().toLowerCase());
    }

    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        GeneroUuid generoUuid = (GeneroUuid) o;
        return Objects.equals(value, generoUuid.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
