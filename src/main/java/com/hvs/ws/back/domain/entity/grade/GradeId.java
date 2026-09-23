package com.hvs.ws.back.domain.entity.grade;

import com.hvs.ws.back.domain.entity.Identifier;
import java.util.Objects;

public class GradeId extends Identifier {

    private final Long value;

    private GradeId(final Long value) {

        this.value = value;
    }

    public static GradeId from(final Long aId) {

        return new GradeId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        GradeId gradeId = (GradeId) o;

        return Objects.equals(value, gradeId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}