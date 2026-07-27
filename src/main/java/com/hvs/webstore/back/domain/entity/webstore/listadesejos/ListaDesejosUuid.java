package com.hvs.webstore.back.domain.entity.webstore.listadesejos;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class ListaDesejosUuid extends Identifier {

    private final String value;

    private ListaDesejosUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static ListaDesejosUuid unique() {

        return new ListaDesejosUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static ListaDesejosUuid from(final String aId) {

        return new ListaDesejosUuid(aId);

    }

    public static ListaDesejosUuid from(final UUID aId) {

        return new ListaDesejosUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        ListaDesejosUuid listaDesejosUuid = (ListaDesejosUuid) o;

        return Objects.equals(value, listaDesejosUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}