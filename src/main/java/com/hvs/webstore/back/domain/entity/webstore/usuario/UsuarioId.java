package com.hvs.webstore.back.domain.entity.webstore.usuario;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class UsuarioId extends Identifier {

    private final Long value;

    private UsuarioId(final Long value) {

        this.value = value;
    }

    public static UsuarioId from(final Long aId) {

        return new UsuarioId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        UsuarioId usuarioId = (UsuarioId) o;

        return Objects.equals(value, usuarioId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}