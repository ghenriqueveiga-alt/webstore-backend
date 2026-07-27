package com.hvs.webstore.back.domain.entity.webstore.cartao;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class CartaoId extends Identifier {

    private final Long value;

    private CartaoId(final Long value) {

        this.value = value;
    }

    public static CartaoId from(final Long aId) {

        return new CartaoId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        CartaoId cartaoId = (CartaoId) o;

        return Objects.equals(value, cartaoId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}