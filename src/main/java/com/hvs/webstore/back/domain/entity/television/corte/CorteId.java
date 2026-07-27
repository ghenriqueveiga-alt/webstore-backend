package com.hvs.webstore.back.domain.entity.television.corte;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class CorteId extends Identifier {

    private final Long value;

    private CorteId(final Long value) {

        this.value = value;
    }

    public static CorteId from(final Long aId) {

        return new CorteId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        CorteId corteId = (CorteId) o;

        return Objects.equals(value, corteId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}