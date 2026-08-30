package com.hvs.webstore.back.domain.entity.television.endingdetectado;

import com.hvs.webstore.back.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class EndingDetectadoUuid extends Identifier {

    private final String value;

    private EndingDetectadoUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static EndingDetectadoUuid unique() {

        return new EndingDetectadoUuid(UUID.randomUUID().toString().toLowerCase());
    }

    public static EndingDetectadoUuid from(final String aValue) {

        return new EndingDetectadoUuid(aValue);
    }

    public static EndingDetectadoUuid from(final UUID aValue) {

        return new EndingDetectadoUuid(aValue.toString().toLowerCase());
    }

    public String getValue() {

        return this.value;
    }

    @Override
    public boolean equals(final Object o) {

        if (o == null || this.getClass() != o.getClass())
            return false;

        EndingDetectadoUuid that = (EndingDetectadoUuid) o;

        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(this.value);
    }
}
