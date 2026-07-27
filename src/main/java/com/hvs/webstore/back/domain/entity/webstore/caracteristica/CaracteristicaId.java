package com.hvs.webstore.back.domain.entity.webstore.caracteristica;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class CaracteristicaId extends Identifier {

    private final Long value;

    private CaracteristicaId(final Long value) {

        this.value = value;
    }

    public static CaracteristicaId from(final Long aId) {

        return new CaracteristicaId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        CaracteristicaId caracteristicaId = (CaracteristicaId) o;

        return Objects.equals(value, caracteristicaId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}