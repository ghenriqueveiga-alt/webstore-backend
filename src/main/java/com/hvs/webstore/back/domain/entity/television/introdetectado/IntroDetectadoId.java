package com.hvs.webstore.back.domain.entity.television.introdetectado;

import com.hvs.webstore.back.domain.Identifier;

import java.util.Objects;

public class IntroDetectadoId extends Identifier {

    private final Long value;

    private IntroDetectadoId(final Long aId) {

        this.value = aId;
    }

    public static IntroDetectadoId from(final Long aId) {

        return new IntroDetectadoId(aId);
    }

    public Long getValue() {

        return this.value;
    }

    @Override
    public boolean equals(final Object o) {

        if (o == null || this.getClass() != o.getClass())
            return false;

        IntroDetectadoId that = (IntroDetectadoId) o;

        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(this.value);
    }
}
