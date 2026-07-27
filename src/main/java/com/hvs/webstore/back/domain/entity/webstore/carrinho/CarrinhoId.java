package com.hvs.webstore.back.domain.entity.webstore.carrinho;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class CarrinhoId extends Identifier {

    private final Long value;

    private CarrinhoId(final Long value) {

        this.value = value;
    }

    public static CarrinhoId from(final Long aId) {

        return new CarrinhoId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        CarrinhoId carrinhoId = (CarrinhoId) o;

        return Objects.equals(value, carrinhoId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}