package com.hvs.webstore.back.domain.entity.television.episodio;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class EpisodioId extends Identifier {

    private final Long value;

    private EpisodioId(final Long value) {

        this.value = value;
    }

    public static EpisodioId from(final Long aId) {

        return new EpisodioId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        EpisodioId episodioId = (EpisodioId) o;

        return Objects.equals(value, episodioId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}