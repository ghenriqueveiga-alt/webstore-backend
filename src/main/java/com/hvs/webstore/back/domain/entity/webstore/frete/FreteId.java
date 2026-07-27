package com.hvs.webstore.back.domain.entity.webstore.frete;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class FreteId extends Identifier {

    private final Long value;

    private FreteId(final Long value) {

        this.value = value;
    }

    public static FreteId from(final Long aId) {

        return new FreteId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        FreteId freteId = (FreteId) o;

        return Objects.equals(value, freteId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}