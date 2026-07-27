package com.hvs.webstore.back.domain.entity.webstore.boleto;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class BoletoUuid extends Identifier {

    private final String value;

    private BoletoUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static BoletoUuid unique() {

        return new BoletoUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static BoletoUuid from(final String aId) {

        return new BoletoUuid(aId);

    }

    public static BoletoUuid from(final UUID aId) {

        return new BoletoUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        BoletoUuid boletoUuid = (BoletoUuid) o;

        return Objects.equals(value, boletoUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}