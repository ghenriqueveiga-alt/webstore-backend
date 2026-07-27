package com.hvs.webstore.back.domain.entity.webstore.precopromocional;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class PrecoPromocionalId extends Identifier {

    private final Long value;

    private PrecoPromocionalId(final Long value) {

        this.value = value;
    }

    public static PrecoPromocionalId from(final Long aId) {

        return new PrecoPromocionalId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        PrecoPromocionalId precoPromocionalId = (PrecoPromocionalId) o;

        return Objects.equals(value, precoPromocionalId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}