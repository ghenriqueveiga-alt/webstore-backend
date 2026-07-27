package com.hvs.webstore.back.domain.entity.webstore.pix;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class PixUuid extends Identifier {

    private final String value;

    private PixUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static PixUuid unique() {

        return new PixUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static PixUuid from(final String aId) {

        return new PixUuid(aId);

    }

    public static PixUuid from(final UUID aId) {

        return new PixUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        PixUuid pixUuid = (PixUuid) o;

        return Objects.equals(value, pixUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}