package com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class CategoriaHierarquiaUuid extends Identifier {

    private final String value;

    private CategoriaHierarquiaUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static CategoriaHierarquiaUuid unique() {

        return new CategoriaHierarquiaUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static CategoriaHierarquiaUuid from(final String aId) {

        return new CategoriaHierarquiaUuid(aId);

    }

    public static CategoriaHierarquiaUuid from(final UUID aId) {

        return new CategoriaHierarquiaUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        CategoriaHierarquiaUuid categoriaHierarquiaUuid = (CategoriaHierarquiaUuid) o;

        return Objects.equals(value, categoriaHierarquiaUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}