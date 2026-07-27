package com.hvs.webstore.back.domain.entity.webstore.transportadora;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class TransportadoraId extends Identifier {

    private final Long value;

    private TransportadoraId(final Long value) {

        this.value = value;
    }

    public static TransportadoraId from(final Long aId) {

        return new TransportadoraId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        TransportadoraId transportadoraId = (TransportadoraId) o;

        return Objects.equals(value, transportadoraId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}