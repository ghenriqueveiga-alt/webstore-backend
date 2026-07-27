package com.hvs.webstore.back.domain.entity.webstore.video;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class VideoUuid extends Identifier {

    private final String value;

    private VideoUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static VideoUuid unique() {

        return new VideoUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static VideoUuid from(final String aId) {

        return new VideoUuid(aId);

    }

    public static VideoUuid from(final UUID aId) {

        return new VideoUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        VideoUuid videoUuid = (VideoUuid) o;

        return Objects.equals(value, videoUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}