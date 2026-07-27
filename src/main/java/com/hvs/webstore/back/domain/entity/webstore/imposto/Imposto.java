package com.hvs.webstore.back.domain.entity.webstore.imposto;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.util.Objects;

public class Imposto extends Entity<ImpostoId> {

    private final ImpostoUuid uuid;
    private final ImpostoStatus statusCode;
    private final String nome;
    private final TipoImposto tipoImposto;
    private final Integer aliquota;
    private final String descricao;

    private Imposto(final ImpostoId id,
                    final ImpostoUuid uuid,
                    final ImpostoStatus statusCode,
                    final String nome,
                    final TipoImposto tipoImposto,
                    final Integer aliquota,
                    final String descricao) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.nome = nome;
        this.tipoImposto = tipoImposto;
        this.aliquota = aliquota;
        this.descricao = descricao;
    }

    public static Imposto create(final String aNome,
                                 final String aTipoDesc,
                                 final Integer aAliquota,
                                 final String aDescricao) {

        return new Imposto(
                ImpostoId.from(-1L),
                ImpostoUuid.unique(),
                ImpostoStatus.ACTIVE,
                aNome,
                aTipoDesc != null ? TipoImposto.findByDesc(aTipoDesc) : null,
                aAliquota,
                aDescricao);
    }

    public static Imposto update(final Long aId,
                                 final String aUuid,
                                 final String aStatusCode,
                                 final String aNome,
                                 final String aTipoCode,
                                 final Integer aAliquota,
                                 final String aDescricao) {

        return new Imposto(
                aId != null ? ImpostoId.from(aId) : null,
                aUuid != null ? ImpostoUuid.from(aUuid) : null,
                aStatusCode != null ? ImpostoStatus.findByCode(aStatusCode) : null,
                aNome,
                aTipoCode != null ? TipoImposto.findByCode(aTipoCode) : null,
                aAliquota,
                aDescricao);
    }

    public static Imposto patch(final String aStatusCode,
                                final String aNome,
                                final String aTipoCode,
                                final Integer aAliquota,
                                final String aDescricao,
                                final Imposto aExisting) {

        return new Imposto(
                aExisting.getId(),
                aExisting.getUuid(),
                aStatusCode != null ? ImpostoStatus.findByCode(aStatusCode) : aExisting.getStatusCode(),
                aNome != null ? aNome : aExisting.getNome(),
                aTipoCode != null ? TipoImposto.findByCode(aTipoCode) : aExisting.getTipoImposto(),
                aAliquota != null ? aAliquota : aExisting.getAliquota(),
                aDescricao != null ? aDescricao : aExisting.getDescricao());
    }

    public static Imposto from(final Long aId,
                               final String aUuid,
                               final String aStatusDesc,
                               final String aNome,
                               final String aTipoDesc,
                               final Integer aAliquota,
                               final String aDescricao) {

        return new Imposto(
                aId != null ? ImpostoId.from(aId) : null,
                aUuid != null ? ImpostoUuid.from(aUuid) : null,
                aStatusDesc != null ? ImpostoStatus.findByDesc(aStatusDesc) : null,
                aNome,
                aTipoDesc != null ? TipoImposto.findByDesc(aTipoDesc) : null,
                aAliquota,
                aDescricao);
    }

    public static Imposto from(final Long aId) {

        return new Imposto(
                aId != null ? ImpostoId.from(aId) : null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public static Imposto from(final String aUuid) {

        return new Imposto(
                null,
                aUuid != null ? ImpostoUuid.from(aUuid) : null,
                null,
                null,
                null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new ImpostoValidator(aHandler, this).validate();
    }

    public ImpostoUuid getUuid() {
        return uuid;
    }
    public ImpostoStatus getStatusCode() {
        return statusCode;
    }
    public String getNome() {
        return nome;
    }
    public TipoImposto getTipoImposto() {
        return tipoImposto;
    }
    public Integer getAliquota() {
        return aliquota;
    }
    public String getDescricao() {
        return descricao;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Imposto imposto = (Imposto) o;

        return Objects.equals(uuid, imposto.uuid) &&
                statusCode == imposto.statusCode &&
                Objects.equals(nome, imposto.nome) &&
                tipoImposto == imposto.tipoImposto &&
                Objects.equals(aliquota, imposto.aliquota) &&
                Objects.equals(descricao, imposto.descricao);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                nome,
                tipoImposto,
                aliquota,
                descricao);
    }
}
