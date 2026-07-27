package com.hvs.webstore.back.domain.entity.webstore.carrinhofrete;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class CarrinhoFreteId extends Identifier {

    private final Long value;

    private CarrinhoFreteId(final Long value) {

        this.value = value;
    }

    public static CarrinhoFreteId from(final Long aId) {

        return new CarrinhoFreteId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        CarrinhoFreteId carrinhoFreteId = (CarrinhoFreteId) o;

        return Objects.equals(value, carrinhoFreteId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}