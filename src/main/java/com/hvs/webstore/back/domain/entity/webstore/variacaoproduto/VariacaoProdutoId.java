package com.hvs.webstore.back.domain.entity.webstore.variacaoproduto;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class VariacaoProdutoId extends Identifier {

    private final Long value;

    private VariacaoProdutoId(final Long value) {

        this.value = value;
    }

    public static VariacaoProdutoId from(final Long aId) {

        return new VariacaoProdutoId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        VariacaoProdutoId variacaoProdutoId = (VariacaoProdutoId) o;

        return Objects.equals(value, variacaoProdutoId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}