package com.hvs.webstore.back.domain.entity.webstore.carrinho;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class ItemCarrinhoId extends Identifier {

    private final Long value;

    private ItemCarrinhoId(final Long value) {

        this.value = value;
    }

    public static ItemCarrinhoId from(final Long aId) {

        return new ItemCarrinhoId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        ItemCarrinhoId itemCarrinhoId = (ItemCarrinhoId) o;

        return Objects.equals(value, itemCarrinhoId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}