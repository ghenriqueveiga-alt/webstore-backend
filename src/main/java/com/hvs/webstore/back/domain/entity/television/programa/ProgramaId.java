package com.hvs.webstore.back.domain.entity.television.programa;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class ProgramaId extends Identifier {

    private final Long value;

    private ProgramaId(final Long value) {

        this.value = value;
    }

    public static ProgramaId from(final Long aId) {

        return new ProgramaId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        ProgramaId programaId = (ProgramaId) o;

        return Objects.equals(value, programaId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}