package com.hvs.webstore.back.domain.entity.television.programa;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class ProgramaUuid extends Identifier {

    private final String value;

    private ProgramaUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static ProgramaUuid unique() {

        return new ProgramaUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static ProgramaUuid from(final String aId) {

        return new ProgramaUuid(aId);

    }

    public static ProgramaUuid from(final UUID aId) {

        return new ProgramaUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        ProgramaUuid programaUuid = (ProgramaUuid) o;

        return Objects.equals(value, programaUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}