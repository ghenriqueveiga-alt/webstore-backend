package com.hvs.webstore.back.domain.entity.television.episodio;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class EpisodioUuid extends Identifier {

    private final String value;

    private EpisodioUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static EpisodioUuid unique() {

        return new EpisodioUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static EpisodioUuid from(final String aId) {

        return new EpisodioUuid(aId);

    }

    public static EpisodioUuid from(final UUID aId) {

        return new EpisodioUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        EpisodioUuid episodioUuid = (EpisodioUuid) o;

        return Objects.equals(value, episodioUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}