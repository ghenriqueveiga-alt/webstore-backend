package com.hvs.webstore.back.domain.entity.webstore.imposto;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class ImpostoId extends Identifier {

    private final Long value;

    private ImpostoId(final Long value) {

        this.value = value;
    }

    public static ImpostoId from(final Long aId) {

        return new ImpostoId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        ImpostoId impostoId = (ImpostoId) o;

        return Objects.equals(value, impostoId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}