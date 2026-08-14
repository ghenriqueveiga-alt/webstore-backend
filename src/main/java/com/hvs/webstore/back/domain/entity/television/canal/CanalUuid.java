package com.hvs.webstore.back.domain.entity.television.canal;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class CanalUuid extends Identifier {

    private final String value;

    private CanalUuid(final String aValue) {
        this.value = Objects.requireNonNull(aValue);
    }

    public static CanalUuid unique() {
        return new CanalUuid(UUID.randomUUID().toString().toLowerCase());
    }

    public static CanalUuid from(final String aId) {
        return new CanalUuid(aId);
    }

    public static CanalUuid from(final UUID aId) {
        return new CanalUuid(aId.toString().toLowerCase());
    }

    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        CanalUuid canalUuid = (CanalUuid) o;
        return Objects.equals(value, canalUuid.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
