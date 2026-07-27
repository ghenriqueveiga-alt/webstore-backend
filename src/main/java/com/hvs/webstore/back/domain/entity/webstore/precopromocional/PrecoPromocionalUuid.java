package com.hvs.webstore.back.domain.entity.webstore.precopromocional;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class PrecoPromocionalUuid extends Identifier {

    private final String value;

    private PrecoPromocionalUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static PrecoPromocionalUuid unique() {

        return new PrecoPromocionalUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static PrecoPromocionalUuid from(final String aId) {

        return new PrecoPromocionalUuid(aId);

    }

    public static PrecoPromocionalUuid from(final UUID aId) {

        return new PrecoPromocionalUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        PrecoPromocionalUuid precoPromocionalUuid = (PrecoPromocionalUuid) o;

        return Objects.equals(value, precoPromocionalUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}