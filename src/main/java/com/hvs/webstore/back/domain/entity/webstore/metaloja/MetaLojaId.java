package com.hvs.webstore.back.domain.entity.webstore.metaloja;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class MetaLojaId extends Identifier {

    private final Long value;

    private MetaLojaId(final Long value) {

        this.value = value;
    }

    public static MetaLojaId from(final Long aId) {

        return new MetaLojaId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        MetaLojaId metaLojaId = (MetaLojaId) o;

        return Objects.equals(value, metaLojaId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}