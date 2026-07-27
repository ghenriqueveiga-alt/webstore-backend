package com.hvs.webstore.back.domain.entity.webstore.endereco;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class EnderecoId extends Identifier {

    private final Long value;

    private EnderecoId(final Long value) {

        this.value = value;
    }

    public static EnderecoId from(final Long aId) {

        return new EnderecoId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        EnderecoId enderecoId = (EnderecoId) o;

        return Objects.equals(value, enderecoId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}