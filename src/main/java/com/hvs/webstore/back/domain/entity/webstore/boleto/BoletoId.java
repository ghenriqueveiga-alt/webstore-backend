package com.hvs.webstore.back.domain.entity.webstore.boleto;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class BoletoId extends Identifier {

    private final Long value;

    private BoletoId(final Long value) {

        this.value = value;
    }

    public static BoletoId from(final Long aId) {

        return new BoletoId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        BoletoId boletoId = (BoletoId) o;

        return Objects.equals(value, boletoId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}