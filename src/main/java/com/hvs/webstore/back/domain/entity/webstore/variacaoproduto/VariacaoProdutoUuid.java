package com.hvs.webstore.back.domain.entity.webstore.variacaoproduto;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class VariacaoProdutoUuid extends Identifier {

    private final String value;

    private VariacaoProdutoUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static VariacaoProdutoUuid unique() {

        return new VariacaoProdutoUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static VariacaoProdutoUuid from(final String aId) {

        return new VariacaoProdutoUuid(aId);

    }

    public static VariacaoProdutoUuid from(final UUID aId) {

        return new VariacaoProdutoUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        VariacaoProdutoUuid variacaoProdutoUuid = (VariacaoProdutoUuid) o;

        return Objects.equals(value, variacaoProdutoUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}