package com.hvs.webstore.back.domain.entity.webstore.role;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class RoleId extends Identifier {

    private final Long value;

    private RoleId(final Long value) {

        this.value = value;
    }

    public static RoleId from(final Long aId) {

        return new RoleId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        RoleId roleId = (RoleId) o;

        return Objects.equals(value, roleId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}