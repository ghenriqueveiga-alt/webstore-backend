package com.hvs.webstore.back.domain.entity.webstore.pix;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class PixId extends Identifier {

    private final Long value;

    private PixId(final Long value) {

        this.value = value;
    }

    public static PixId from(final Long aId) {

        return new PixId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        PixId pixId = (PixId) o;

        return Objects.equals(value, pixId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}