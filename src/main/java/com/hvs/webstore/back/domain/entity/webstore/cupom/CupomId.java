package com.hvs.webstore.back.domain.entity.webstore.cupom;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class CupomId extends Identifier {

    private final Long value;

    private CupomId(final Long value) {

        this.value = value;
    }

    public static CupomId from(final Long aId) {

        return new CupomId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        CupomId cupomId = (CupomId) o;

        return Objects.equals(value, cupomId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}