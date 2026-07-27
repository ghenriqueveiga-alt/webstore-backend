package com.hvs.webstore.back.domain.entity.webstore.marca;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class MarcaId extends Identifier {

    private final Long value;

    private MarcaId(final Long value) {

        this.value = value;
    }

    public static MarcaId from(final Long aId) {

        return new MarcaId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        MarcaId marcaId = (MarcaId) o;

        return Objects.equals(value, marcaId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}