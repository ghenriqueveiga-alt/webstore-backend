package com.hvs.webstore.back.domain.entity.webstore.video;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class VideoId extends Identifier {

    private final Long value;

    private VideoId(final Long value) {

        this.value = value;
    }

    public static VideoId from(final Long aId) {

        return new VideoId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        VideoId videoId = (VideoId) o;

        return Objects.equals(value, videoId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}