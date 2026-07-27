package com.hvs.webstore.back.domain.entity.webstore.listadesejos;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class ItemListaDesejosId extends Identifier {

    private final Long value;

    private ItemListaDesejosId(final Long value) {

        this.value = value;
    }

    public static ItemListaDesejosId from(final Long aId) {

        return new ItemListaDesejosId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        ItemListaDesejosId itemListaDesejosId = (ItemListaDesejosId) o;

        return Objects.equals(value, itemListaDesejosId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}