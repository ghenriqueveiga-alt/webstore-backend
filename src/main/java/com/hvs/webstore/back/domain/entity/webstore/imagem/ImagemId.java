package com.hvs.webstore.back.domain.entity.webstore.imagem;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;

public class ImagemId extends Identifier {

    private final Long value;

    private ImagemId(final Long value) {

        this.value = value;
    }

    public static ImagemId from(final Long aId) {

        return new ImagemId(aId);

    }
    public Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        ImagemId imagemId = (ImagemId) o;

        return Objects.equals(value, imagemId.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}