package com.hvs.webstore.back.domain.entity.webstore.carrinho;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class ItemCarrinhoUuid extends Identifier {

    private final String value;

    private ItemCarrinhoUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static ItemCarrinhoUuid unique() {

        return new ItemCarrinhoUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static ItemCarrinhoUuid from(final String aId) {

        return new ItemCarrinhoUuid(aId);

    }

    public static ItemCarrinhoUuid from(final UUID aId) {

        return new ItemCarrinhoUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        ItemCarrinhoUuid itemCarrinhoUuid = (ItemCarrinhoUuid) o;

        return Objects.equals(value, itemCarrinhoUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}