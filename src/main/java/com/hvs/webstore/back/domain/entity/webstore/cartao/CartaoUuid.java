package com.hvs.webstore.back.domain.entity.webstore.cartao;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class CartaoUuid extends Identifier {

    private final String value;

    private CartaoUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static CartaoUuid unique() {

        return new CartaoUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static CartaoUuid from(final String aId) {

        return new CartaoUuid(aId);

    }

    public static CartaoUuid from(final UUID aId) {

        return new CartaoUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        CartaoUuid cartaoUuid = (CartaoUuid) o;

        return Objects.equals(value, cartaoUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}