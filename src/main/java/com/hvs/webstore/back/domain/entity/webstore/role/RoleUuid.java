package com.hvs.webstore.back.domain.entity.webstore.role;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class RoleUuid extends Identifier {

    private final String value;

    private RoleUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static RoleUuid unique() {

        return new RoleUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static RoleUuid from(final String aId) {

        return new RoleUuid(aId);

    }

    public static RoleUuid from(final UUID aId) {

        return new RoleUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        RoleUuid roleUuid = (RoleUuid) o;

        return Objects.equals(value, roleUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}