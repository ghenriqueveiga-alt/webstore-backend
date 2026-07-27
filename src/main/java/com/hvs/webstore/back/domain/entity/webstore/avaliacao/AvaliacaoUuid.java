package com.hvs.webstore.back.domain.entity.webstore.avaliacao;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class AvaliacaoUuid extends Identifier {

    private final String value;

    private AvaliacaoUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static AvaliacaoUuid unique() {

        return new AvaliacaoUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static AvaliacaoUuid from(final String aId) {

        return new AvaliacaoUuid(aId);

    }

    public static AvaliacaoUuid from(final UUID aId) {

        return new AvaliacaoUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        AvaliacaoUuid avaliacaoUuid = (AvaliacaoUuid) o;

        return Objects.equals(value, avaliacaoUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}