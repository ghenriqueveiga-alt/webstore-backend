package com.hvs.webstore.back.domain.entity.webstore.endereco;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class EnderecoUuid extends Identifier {

    private final String value;

    private EnderecoUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static EnderecoUuid unique() {

        return new EnderecoUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static EnderecoUuid from(final String aId) {

        return new EnderecoUuid(aId);

    }

    public static EnderecoUuid from(final UUID aId) {

        return new EnderecoUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        EnderecoUuid enderecoUuid = (EnderecoUuid) o;

        return Objects.equals(value, enderecoUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}