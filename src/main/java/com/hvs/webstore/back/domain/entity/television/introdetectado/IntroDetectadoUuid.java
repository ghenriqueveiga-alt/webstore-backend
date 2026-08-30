package com.hvs.webstore.back.domain.entity.television.introdetectado;

import com.hvs.webstore.back.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class IntroDetectadoUuid extends Identifier {

    private final String value;

    private IntroDetectadoUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static IntroDetectadoUuid unique() {

        return new IntroDetectadoUuid(UUID.randomUUID().toString().toLowerCase());
    }

    public static IntroDetectadoUuid from(final String aValue) {

        return new IntroDetectadoUuid(aValue);
    }

    public static IntroDetectadoUuid from(final UUID aValue) {

        return new IntroDetectadoUuid(aValue.toString().toLowerCase());
    }

    public String getValue() {

        return this.value;
    }

    @Override
    public boolean equals(final Object o) {

        if (o == null || this.getClass() != o.getClass())
            return false;

        IntroDetectadoUuid that = (IntroDetectadoUuid) o;

        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(this.value);
    }
}
