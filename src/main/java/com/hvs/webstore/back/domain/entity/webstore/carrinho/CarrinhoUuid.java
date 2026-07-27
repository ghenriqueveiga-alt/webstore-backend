package com.hvs.webstore.back.domain.entity.webstore.carrinho;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class CarrinhoUuid extends Identifier {

    private final String value;

    private CarrinhoUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static CarrinhoUuid unique() {

        return new CarrinhoUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static CarrinhoUuid from(final String aId) {

        return new CarrinhoUuid(aId);

    }

    public static CarrinhoUuid from(final UUID aId) {

        return new CarrinhoUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        CarrinhoUuid carrinhoUuid = (CarrinhoUuid) o;

        return Objects.equals(value, carrinhoUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}