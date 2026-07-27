package com.hvs.webstore.back.domain.entity.webstore.produto;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class ProdutoUuid extends Identifier {

    private final String value;

    private ProdutoUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static ProdutoUuid unique() {

        return new ProdutoUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static ProdutoUuid from(final String aId) {

        return new ProdutoUuid(aId);

    }

    public static ProdutoUuid from(final UUID aId) {

        return new ProdutoUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        ProdutoUuid produtoUuid = (ProdutoUuid) o;

        return Objects.equals(value, produtoUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}