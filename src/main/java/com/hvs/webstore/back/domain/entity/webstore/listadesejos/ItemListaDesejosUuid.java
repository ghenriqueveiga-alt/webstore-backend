package com.hvs.webstore.back.domain.entity.webstore.listadesejos;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class ItemListaDesejosUuid extends Identifier {

    private final String value;

    private ItemListaDesejosUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static ItemListaDesejosUuid unique() {

        return new ItemListaDesejosUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static ItemListaDesejosUuid from(final String aId) {

        return new ItemListaDesejosUuid(aId);

    }

    public static ItemListaDesejosUuid from(final UUID aId) {

        return new ItemListaDesejosUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        ItemListaDesejosUuid itemListaDesejosUuid = (ItemListaDesejosUuid) o;

        return Objects.equals(value, itemListaDesejosUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}