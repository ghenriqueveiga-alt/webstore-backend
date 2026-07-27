package com.hvs.webstore.back.domain.entity.webstore.imposto;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class ImpostoUuid extends Identifier {

    private final String value;

    private ImpostoUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static ImpostoUuid unique() {

        return new ImpostoUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static ImpostoUuid from(final String aId) {

        return new ImpostoUuid(aId);

    }

    public static ImpostoUuid from(final UUID aId) {

        return new ImpostoUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        ImpostoUuid impostoUuid = (ImpostoUuid) o;

        return Objects.equals(value, impostoUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}