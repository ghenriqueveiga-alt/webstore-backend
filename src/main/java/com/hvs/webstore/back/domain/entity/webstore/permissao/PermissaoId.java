package com.hvs.webstore.back.domain.entity.webstore.permissao;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class PermissaoId extends Identifier {

    private final Long value;

    private PermissaoId(final Long value) {

        this.value = value;
    }

    public static PermissaoId from(final Long aId) {

        return new PermissaoId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        PermissaoId permissaoId = (PermissaoId) o;

        return Objects.equals(value, permissaoId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}