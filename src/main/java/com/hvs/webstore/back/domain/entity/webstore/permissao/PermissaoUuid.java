package com.hvs.webstore.back.domain.entity.webstore.permissao;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class PermissaoUuid extends Identifier {

    private final String value;

    private PermissaoUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static PermissaoUuid unique() {

        return new PermissaoUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static PermissaoUuid from(final String aId) {

        return new PermissaoUuid(aId);

    }

    public static PermissaoUuid from(final UUID aId) {

        return new PermissaoUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        PermissaoUuid permissaoUuid = (PermissaoUuid) o;

        return Objects.equals(value, permissaoUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}