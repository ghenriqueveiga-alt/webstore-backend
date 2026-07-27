package com.hvs.webstore.back.domain.entity.webstore.carrinhofrete;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class CarrinhoFreteUuid extends Identifier {

    private final String value;

    private CarrinhoFreteUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static CarrinhoFreteUuid unique() {

        return new CarrinhoFreteUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static CarrinhoFreteUuid from(final String aId) {

        return new CarrinhoFreteUuid(aId);

    }

    public static CarrinhoFreteUuid from(final UUID aId) {

        return new CarrinhoFreteUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        CarrinhoFreteUuid carrinhoFreteUuid = (CarrinhoFreteUuid) o;

        return Objects.equals(value, carrinhoFreteUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}