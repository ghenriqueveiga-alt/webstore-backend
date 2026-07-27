package com.hvs.webstore.back.domain.entity.webstore.caracteristica;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class CaracteristicaUuid extends Identifier {

    private final String value;

    private CaracteristicaUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static CaracteristicaUuid unique() {

        return new CaracteristicaUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static CaracteristicaUuid from(final String aId) {

        return new CaracteristicaUuid(aId);

    }

    public static CaracteristicaUuid from(final UUID aId) {

        return new CaracteristicaUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        CaracteristicaUuid caracteristicaUuid = (CaracteristicaUuid) o;

        return Objects.equals(value, caracteristicaUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}