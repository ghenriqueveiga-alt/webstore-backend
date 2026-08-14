package com.hvs.webstore.back.domain.entity.television.genero;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class GeneroId extends Identifier {

    private final Long value;

    private GeneroId(final Long value) {
        this.value = value;
    }

    public static GeneroId from(final Long aId) {
        return new GeneroId(aId);
    }

    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        GeneroId generoId = (GeneroId) o;
        return Objects.equals(value, generoId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
