package com.hvs.webstore.back.domain.entity.webstore.listadesejos;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class ListaDesejosId extends Identifier {

    private final Long value;

    private ListaDesejosId(final Long value) {

        this.value = value;
    }

    public static ListaDesejosId from(final Long aId) {

        return new ListaDesejosId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        ListaDesejosId listaDesejosId = (ListaDesejosId) o;

        return Objects.equals(value, listaDesejosId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}