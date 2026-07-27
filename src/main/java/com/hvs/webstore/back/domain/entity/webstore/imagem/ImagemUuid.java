package com.hvs.webstore.back.domain.entity.webstore.imagem;

import com.hvs.webstore.back.domain.Identifier;
import java.util.Objects;
import java.util.UUID;

public class ImagemUuid extends Identifier {

    private final String value;

    private ImagemUuid(final String aValue) {

        this.value = Objects.requireNonNull(aValue);
    }

    public static ImagemUuid unique() {

        return new ImagemUuid(UUID.randomUUID().toString().toLowerCase());

    }

    public static ImagemUuid from(final String aId) {

        return new ImagemUuid(aId);

    }

    public static ImagemUuid from(final UUID aId) {

        return new ImagemUuid(aId.toString().toLowerCase());

    }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        ImagemUuid imagemUuid = (ImagemUuid) o;

        return Objects.equals(value, imagemUuid.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}