package com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class CategoriaHierarquiaId extends Identifier {

    private final Long value;

    private CategoriaHierarquiaId(final Long value) {

        this.value = value;
    }

    public static CategoriaHierarquiaId from(final Long aId) {

        return new CategoriaHierarquiaId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        CategoriaHierarquiaId categoriaHierarquiaId = (CategoriaHierarquiaId) o;

        return Objects.equals(value, categoriaHierarquiaId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}