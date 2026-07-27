package com.hvs.webstore.back.domain.entity.webstore.logauditoria;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class LogAuditoriaUuid extends Identifier {

    private final String value;

    private LogAuditoriaUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static LogAuditoriaUuid unique() {

        return new LogAuditoriaUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static LogAuditoriaUuid from(final String aId) {

        return new LogAuditoriaUuid(aId);

    }

    public static LogAuditoriaUuid from(final UUID aId) {

        return new LogAuditoriaUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        LogAuditoriaUuid logAuditoriaUuid = (LogAuditoriaUuid) o;

        return Objects.equals(value, logAuditoriaUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}