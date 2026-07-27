package com.hvs.webstore.back.domain.entity.webstore.produto;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class ProdutoId extends Identifier {

    private final Long value;

    private ProdutoId(final Long value) {

        this.value = value;
    }

    public static ProdutoId from(final Long aId) {

        return new ProdutoId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        ProdutoId produtoId = (ProdutoId) o;

        return Objects.equals(value, produtoId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}