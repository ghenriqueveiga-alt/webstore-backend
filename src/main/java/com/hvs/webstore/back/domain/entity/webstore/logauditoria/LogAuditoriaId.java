package com.hvs.webstore.back.domain.entity.webstore.logauditoria;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class LogAuditoriaId extends Identifier {

    private final Long value;

    private LogAuditoriaId(final Long value) {

        this.value = value;
    }

    public static LogAuditoriaId from(final Long aId) {

        return new LogAuditoriaId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        LogAuditoriaId logAuditoriaId = (LogAuditoriaId) o;

        return Objects.equals(value, logAuditoriaId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}