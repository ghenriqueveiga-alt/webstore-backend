package com.hvs.webstore.back.domain.entity.webstore.usuario;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class UsuarioUuid extends Identifier {

    private final String value;

    private UsuarioUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static UsuarioUuid unique() {

        return new UsuarioUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static UsuarioUuid from(final String aId) {

        return new UsuarioUuid(aId);

    }

    public static UsuarioUuid from(final UUID aId) {

        return new UsuarioUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        UsuarioUuid usuarioUuid = (UsuarioUuid) o;

        return Objects.equals(value, usuarioUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}