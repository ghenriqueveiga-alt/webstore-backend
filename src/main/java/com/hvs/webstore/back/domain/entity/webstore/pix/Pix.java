package com.hvs.webstore.back.domain.entity.webstore.pix;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.util.Objects;

public class Pix extends Entity<PixId> {

    private final PixUuid uuid;
    private final PixStatus statusCode;
    private final String chavePix;
    private final TipoChavePix tipoChavePix;

    private Pix(final PixId id,
                final PixUuid uuid,
                final PixStatus statusCode,
                final String chavePix,
                final TipoChavePix tipoChavePix) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.chavePix = chavePix;
        this.tipoChavePix = tipoChavePix;
    }

    public static Pix create(final String aChavePix,
                             final String aTipoChavePix) {

        final var id = PixId.from(-1L);
        final var uuid = PixUuid.unique();
        final var status = PixStatus.ACTIVE;
        final var tipoChavePix = aTipoChavePix != null ? TipoChavePix.findByDesc(aTipoChavePix) : null;

        return new Pix(
                id,
                uuid,
                status,
                aChavePix,
                tipoChavePix);
    }

    public static Pix update(final Long aId,
                             final String aUuid,
                             final String aStatusCode,
                             final String aChavePix,
                             final String aTipoChavePix) {

        final var id = aId != null ? PixId.from(aId) : null;
        final var uuid = aUuid != null ? PixUuid.from(aUuid) : null;
        final var status = aStatusCode != null ? PixStatus.findByCode(aStatusCode) : null;
        final var tipoChavePix = aTipoChavePix != null ? TipoChavePix.findByCode(aTipoChavePix) : null;

        return new Pix(
                id,
                uuid,
                status,
                aChavePix,
                tipoChavePix);
    }

    public static Pix patch(final String aStatusCode,
                            final String aChavePix,
                            final String aTipoChavePix,
                            final Pix aExisting) {

        final var id = aExisting.getId();
        final var uuid = aExisting.getUuid();
        final var status = aStatusCode != null ? PixStatus.findByCode(aStatusCode) : aExisting.getStatusCode();
        final var chavePix = aChavePix != null ? aChavePix : aExisting.getChavePix();
        final var tipoChavePix = aTipoChavePix != null ? TipoChavePix.findByCode(aTipoChavePix) : aExisting.getTipoChavePix();

        return new Pix(
                id,
                uuid,
                status,
                chavePix,
                tipoChavePix);
    }

    public static Pix from(final Long aId,
                           final String aUuid,
                           final String aStatusDesc,
                           final String aChavePix,
                           final String aTipoChavePix) {

        final var id = aId != null ? PixId.from(aId) : null;
        final var uuid = aUuid != null ? PixUuid.from(aUuid) : null;
        final var status = aStatusDesc != null ? PixStatus.findByDesc(aStatusDesc) : null;
        final var tipoChavePix = aTipoChavePix != null ? TipoChavePix.findByDesc(aTipoChavePix) : null;

        return new Pix(
                id,
                uuid,
                status,
                aChavePix,
                tipoChavePix);
    }

    public static Pix from(final Long aId) {

        return new Pix(
                aId != null ? PixId.from(aId) : null,
                null,
                null,
                null,
                null);
    }

    public static Pix from(final String aUuid) {

        return new Pix(
                null,
                aUuid != null ? PixUuid.from(aUuid) : null,
                null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new PixValidator(aHandler, this).validate();
    }

    public PixUuid getUuid() {
        return uuid;
    }
    public PixStatus getStatusCode() {
        return statusCode;
    }
    public String getChavePix() {
        return chavePix;
    }
    public TipoChavePix getTipoChavePix() {
        return tipoChavePix;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Pix pix = (Pix) o;

        return Objects.equals(uuid, pix.uuid) &&
                statusCode == pix.statusCode &&
                Objects.equals(chavePix, pix.chavePix) &&
                Objects.equals(tipoChavePix, pix.tipoChavePix);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                chavePix,
                tipoChavePix);
    }
}
