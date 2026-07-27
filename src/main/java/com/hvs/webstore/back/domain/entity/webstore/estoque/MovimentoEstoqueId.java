package com.hvs.webstore.back.domain.entity.webstore.estoque;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class MovimentoEstoqueId extends Identifier {

    private final Long value;

    private MovimentoEstoqueId(final Long value) {

        this.value = value;
    }

    public static MovimentoEstoqueId from(final Long aId) {

        return new MovimentoEstoqueId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        MovimentoEstoqueId movimentoEstoqueId = (MovimentoEstoqueId) o;

        return Objects.equals(value, movimentoEstoqueId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}