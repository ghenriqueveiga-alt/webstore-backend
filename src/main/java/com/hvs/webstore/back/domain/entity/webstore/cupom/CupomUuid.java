package com.hvs.webstore.back.domain.entity.webstore.cupom;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class CupomUuid extends Identifier {

    private final String value;

    private CupomUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static CupomUuid unique() {

        return new CupomUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static CupomUuid from(final String aId) {

        return new CupomUuid(aId);

    }

    public static CupomUuid from(final UUID aId) {

        return new CupomUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        CupomUuid cupomUuid = (CupomUuid) o;

        return Objects.equals(value, cupomUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}