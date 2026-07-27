package com.hvs.webstore.back.domain.entity.webstore.estoque;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class EstoqueUuid extends Identifier {

    private final String value;

    private EstoqueUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static EstoqueUuid unique() {

        return new EstoqueUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static EstoqueUuid from(final String aId) {

        return new EstoqueUuid(aId);

    }

    public static EstoqueUuid from(final UUID aId) {

        return new EstoqueUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        EstoqueUuid estoqueUuid = (EstoqueUuid) o;

        return Objects.equals(value, estoqueUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}