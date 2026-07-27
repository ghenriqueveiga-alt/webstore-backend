package com.hvs.webstore.back.domain.entity.webstore.avaliacao;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class AvaliacaoId extends Identifier {

    private final Long value;

    private AvaliacaoId(final Long value) {

        this.value = value;
    }

    public static AvaliacaoId from(final Long aId) {

        return new AvaliacaoId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        AvaliacaoId avaliacaoId = (AvaliacaoId) o;

        return Objects.equals(value, avaliacaoId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}