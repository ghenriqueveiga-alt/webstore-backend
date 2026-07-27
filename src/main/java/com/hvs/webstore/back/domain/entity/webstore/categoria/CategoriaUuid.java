package com.hvs.webstore.back.domain.entity.webstore.categoria;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class CategoriaUuid extends Identifier {

    private final String value;

    private CategoriaUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static CategoriaUuid unique() {

        return new CategoriaUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static CategoriaUuid from(final String aId) {

        return new CategoriaUuid(aId);

    }

    public static CategoriaUuid from(final UUID aId) {

        return new CategoriaUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        CategoriaUuid categoriaUuid = (CategoriaUuid) o;

        return Objects.equals(value, categoriaUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}