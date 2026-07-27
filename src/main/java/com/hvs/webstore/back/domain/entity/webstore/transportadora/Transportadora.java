package com.hvs.webstore.back.domain.entity.webstore.transportadora;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.util.Objects;

public class Transportadora extends Entity<TransportadoraId> {

    private final TransportadoraUuid uuid;
    private final TransportadoraStatus statusCode;
    private final String nome;

    private Transportadora(final TransportadoraId id,
                            final TransportadoraUuid uuid,
                            final TransportadoraStatus statusCode,
                            final String nome) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.nome = nome;
    }

    public static Transportadora create(final String aNome) {

        return new Transportadora(
                TransportadoraId.from(-1L),
                TransportadoraUuid.unique(),
                TransportadoraStatus.ACTIVE,
                aNome);
    }

    public static Transportadora from(final Long aId,
                                       final String aUuid,
                                       final String aStatusDesc,
                                       final String aNome) {

        return new Transportadora(
                aId != null ? TransportadoraId.from(aId) : null,
                aUuid != null ? TransportadoraUuid.from(aUuid) : null,
                aStatusDesc != null ? TransportadoraStatus.findByDesc(aStatusDesc) : null,
                aNome);
    }

    public static Transportadora from(final Long aId) {

        return new Transportadora(
                aId != null ? TransportadoraId.from(aId) : null,
                null,
                null,
                null);
    }

    public static Transportadora from(final String aUuid) {

        return new Transportadora(
                null,
                aUuid != null ? TransportadoraUuid.from(aUuid) : null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new TransportadoraValidator(aHandler, this).validate();
    }

    public TransportadoraUuid getUuid() {
        return uuid;
    }
    public TransportadoraStatus getStatusCode() {
        return statusCode;
    }
    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Transportadora that = (Transportadora) o;

        return Objects.equals(uuid, that.uuid) &&
                statusCode == that.statusCode &&
                Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                nome);
    }
}
