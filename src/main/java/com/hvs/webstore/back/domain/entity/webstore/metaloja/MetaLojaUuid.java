package com.hvs.webstore.back.domain.entity.webstore.metaloja;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class MetaLojaUuid extends Identifier {

    private final String value;

    private MetaLojaUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static MetaLojaUuid unique() {

        return new MetaLojaUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static MetaLojaUuid from(final String aId) {

        return new MetaLojaUuid(aId);

    }

    public static MetaLojaUuid from(final UUID aId) {

        return new MetaLojaUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        MetaLojaUuid metaLojaUuid = (MetaLojaUuid) o;

        return Objects.equals(value, metaLojaUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}