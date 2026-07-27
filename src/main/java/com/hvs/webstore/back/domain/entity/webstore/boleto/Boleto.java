package com.hvs.webstore.back.domain.entity.webstore.boleto;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.util.Objects;

public class Boleto extends Entity<BoletoId> {

    private final BoletoUuid uuid;
    private final BoletoStatus statusCode;
    private final String codigoBarras;
    private final String vencimento;

    private Boleto(final BoletoId id,
                   final BoletoUuid uuid,
                   final BoletoStatus statusCode,
                   final String codigoBarras,
                   final String vencimento) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.codigoBarras = codigoBarras;
        this.vencimento = vencimento;
    }

    public static Boleto create(final String aCodigoBarras,
                                final String aVencimento) {

        return new Boleto(
                BoletoId.from(-1L),
                BoletoUuid.unique(),
                BoletoStatus.ACTIVE,
                aCodigoBarras,
                aVencimento);
    }

    public static Boleto update(final Long aId,
                                final String aUuid,
                                final String aStatusCode,
                                final String aCodigoBarras,
                                final String aVencimento) {

        return new Boleto(
                aId != null ? BoletoId.from(aId) : null,
                aUuid != null ? BoletoUuid.from(aUuid) : null,
                aStatusCode != null ? BoletoStatus.findByCode(aStatusCode) : null,
                aCodigoBarras,
                aVencimento);
    }

    public static Boleto patch(final String aStatusCode,
                               final String aCodigoBarras,
                               final String aVencimento,
                               final Boleto aExisting) {

        return new Boleto(
                aExisting.getId(),
                aExisting.getUuid(),
                aStatusCode != null ? BoletoStatus.findByCode(aStatusCode) : aExisting.getStatusCode(),
                aCodigoBarras != null ? aCodigoBarras : aExisting.getCodigoBarras(),
                aVencimento != null ? aVencimento : aExisting.getVencimento());
    }

    public static Boleto from(final Long aId,
                              final String aUuid,
                              final String aStatusDesc,
                              final String aCodigoBarras,
                              final String aVencimento) {

        return new Boleto(
                aId != null ? BoletoId.from(aId) : null,
                aUuid != null ? BoletoUuid.from(aUuid) : null,
                aStatusDesc != null ? BoletoStatus.findByDesc(aStatusDesc) : null,
                aCodigoBarras,
                aVencimento);
    }

    public static Boleto from(final Long aId) {

        return new Boleto(
                aId != null ? BoletoId.from(aId) : null,
                null,
                null,
                null,
                null);
    }

    public static Boleto from(final String aUuid) {

        return new Boleto(
                null,
                aUuid != null ? BoletoUuid.from(aUuid) : null,
                null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new BoletoValidator(aHandler, this).validate();
    }

    public BoletoUuid getUuid() {
        return uuid;
    }
    public BoletoStatus getStatusCode() {
        return statusCode;
    }
    public String getCodigoBarras() {
        return codigoBarras;
    }
    public String getVencimento() {
        return vencimento;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Boleto boleto = (Boleto) o;

        return Objects.equals(uuid, boleto.uuid) &&
                statusCode == boleto.statusCode &&
                Objects.equals(codigoBarras, boleto.codigoBarras) &&
                Objects.equals(vencimento, boleto.vencimento);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                codigoBarras,
                vencimento);
    }
}