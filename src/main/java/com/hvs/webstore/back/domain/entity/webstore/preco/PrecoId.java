package com.hvs.webstore.back.domain.entity.webstore.preco;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class PrecoId extends Identifier {

    private final Long value;

    private PrecoId(final Long value) {

        this.value = value;
    }

    public static PrecoId from(final Long aId) {

        return new PrecoId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        PrecoId precoId = (PrecoId) o;

        return Objects.equals(value, precoId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}