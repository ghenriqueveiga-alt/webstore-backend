package com.hvs.webstore.back.domain.entity.webstore.anexo;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class AnexoId extends Identifier {

    private final Long value;

    private AnexoId(final Long value) {

        this.value = value;
    }

    public static AnexoId from(final Long aId) {

        return new AnexoId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        AnexoId anexoId = (AnexoId) o;

        return Objects.equals(value, anexoId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}
