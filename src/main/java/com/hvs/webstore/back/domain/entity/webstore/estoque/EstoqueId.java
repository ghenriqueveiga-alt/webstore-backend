package com.hvs.webstore.back.domain.entity.webstore.estoque;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class EstoqueId extends Identifier {

    private final Long value;

    private EstoqueId(final Long value) {

        this.value = value;
    }

    public static EstoqueId from(final Long aId) {

        return new EstoqueId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        EstoqueId estoqueId = (EstoqueId) o;

        return Objects.equals(value, estoqueId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}