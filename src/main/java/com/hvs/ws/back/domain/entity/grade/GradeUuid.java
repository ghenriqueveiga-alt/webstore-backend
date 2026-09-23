package com.hvs.ws.back.domain.entity.grade;

import com.hvs.ws.back.domain.entity.Identifier;
import java.util.Objects;
import java.util.UUID;

public class GradeUuid extends Identifier {

    private final String value;

    private GradeUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static GradeUuid unique() {

        return new GradeUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static GradeUuid from(final String aId) {

        return new GradeUuid(aId);

    }

    public static GradeUuid from(final UUID aId) {

        return new GradeUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        GradeUuid gradeUuid = (GradeUuid) o;

        return Objects.equals(value, gradeUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}