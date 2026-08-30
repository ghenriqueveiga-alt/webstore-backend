package com.hvs.webstore.back.domain.entity.television.endingdetectado;

import com.hvs.webstore.back.domain.Identifier;

import java.util.Objects;

public class EndingDetectadoId extends Identifier {

    private final Long value;

    private EndingDetectadoId(final Long aId) {

        this.value = aId;
    }

    public static EndingDetectadoId from(final Long aId) {

        return new EndingDetectadoId(aId);
    }

    public Long getValue() {

        return this.value;
    }

    @Override
    public boolean equals(final Object o) {

        if (o == null || this.getClass() != o.getClass())
            return false;

        EndingDetectadoId that = (EndingDetectadoId) o;

        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(this.value);
    }
}
