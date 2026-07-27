package com.hvs.webstore.back.domain.entity.television.bloco;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class BlocoId extends Identifier {

    private final Long value;

    private BlocoId(final Long value) {

        this.value = value;
    }

    public static BlocoId from(final Long aId) {

        return new BlocoId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        BlocoId blocoId = (BlocoId) o;

        return Objects.equals(value, blocoId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}