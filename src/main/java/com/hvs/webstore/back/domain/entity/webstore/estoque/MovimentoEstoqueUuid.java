package com.hvs.webstore.back.domain.entity.webstore.estoque;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class MovimentoEstoqueUuid extends Identifier {

    private final String value;

    private MovimentoEstoqueUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static MovimentoEstoqueUuid unique() {

        return new MovimentoEstoqueUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static MovimentoEstoqueUuid from(final String aId) {

        return new MovimentoEstoqueUuid(aId);

    }

    public static MovimentoEstoqueUuid from(final UUID aId) {

        return new MovimentoEstoqueUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        MovimentoEstoqueUuid movimentoEstoqueUuid = (MovimentoEstoqueUuid) o;

        return Objects.equals(value, movimentoEstoqueUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}