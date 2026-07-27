package com.hvs.webstore.back.domain.entity.webstore.categoria;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class CategoriaId extends Identifier {

    private final Long value;

    private CategoriaId(final Long value) {

        this.value = value;
    }

    public static CategoriaId from(final Long aId) {

        return new CategoriaId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        CategoriaId categoriaId = (CategoriaId) o;

        return Objects.equals(value, categoriaId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}