package com.hvs.webstore.back.domain.entity.television.canal;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class CanalId extends Identifier {

    private final Long value;

    private CanalId(final Long value) {
        this.value = value;
    }

    public static CanalId from(final Long aId) {
        return new CanalId(aId);
    }

    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        CanalId canalId = (CanalId) o;
        return Objects.equals(value, canalId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
