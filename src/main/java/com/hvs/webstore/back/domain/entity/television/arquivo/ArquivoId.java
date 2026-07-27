package com.hvs.webstore.back.domain.entity.television.arquivo;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class ArquivoId extends Identifier {

    private final Long value;

    private ArquivoId(final Long value) {

        this.value = value;
    }

    public static ArquivoId from(final Long aId) {

        return new ArquivoId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        ArquivoId arquivoId = (ArquivoId) o;

        return Objects.equals(value, arquivoId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}