package com.hvs.webstore.back.domain.entity.webstore.caracteristica;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.webstore.produto.Produto;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.util.Objects;

public class Caracteristica extends Entity<CaracteristicaId> {

    private final CaracteristicaUuid uuid;
    private final CaracteristicaStatus statusCode;
    private final String nome;
    private final String descricao;
    private final Produto produto;

    private Caracteristica(final CaracteristicaId id,
                           final CaracteristicaUuid uuid,
                           final CaracteristicaStatus statusCode,
                           final String nome,
                           final String descricao,
                           final Produto produto) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }

    public static Caracteristica create(final String aNome,
                                         final String aDescricao) {

        return new Caracteristica(
                CaracteristicaId.from(-1L),
                CaracteristicaUuid.unique(),
                CaracteristicaStatus.ACTIVE,
                aNome,
                aDescricao,
                null);
    }

    public static Caracteristica update(final Long aId,
                                         final String aUuid,
                                         final String aStatusCode,
                                         final String aNome,
                                         final String aDescricao) {

        return new Caracteristica(
                aId != null ? CaracteristicaId.from(aId) : null,
                aUuid != null ? CaracteristicaUuid.from(aUuid) : null,
                aStatusCode != null ? CaracteristicaStatus.findByCode(aStatusCode) : null,
                aNome,
                aDescricao,
                null);
    }

    public static Caracteristica patch(final String aStatusCode,
                                        final String aNome,
                                        final String aDescricao,
                                        final Caracteristica aExisting) {

        return new Caracteristica(
                aExisting.getId(),
                aExisting.getUuid(),
                aStatusCode != null ? CaracteristicaStatus.findByCode(aStatusCode) : aExisting.getStatusCode(),
                aNome != null ? aNome : aExisting.getNome(),
                aDescricao != null ? aDescricao : aExisting.getDescricao(),
                aExisting.getProduto());
    }

    public static Caracteristica from(final Long aId,
                                       final String aUuid,
                                       final String aStatusDesc,
                                       final String aNome,
                                       final String aDescricao,
                                       final Produto aProduto) {

        return new Caracteristica(
                aId != null ? CaracteristicaId.from(aId) : null,
                aUuid != null ? CaracteristicaUuid.from(aUuid) : null,
                aStatusDesc != null ? CaracteristicaStatus.findByDesc(aStatusDesc) : null,
                aNome,
                aDescricao,
                aProduto);
    }

    public static Caracteristica from(final Long aId) {

        return new Caracteristica(
                aId != null ? CaracteristicaId.from(aId) : null,
                null,
                null,
                null,
                null,
                null);
    }

    public static Caracteristica from(final String aUuid) {

        return new Caracteristica(
                null,
                aUuid != null ? CaracteristicaUuid.from(aUuid) : null,
                null,
                null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new CaracteristicaValidator(aHandler, this).validate();
    }

    public CaracteristicaUuid getUuid() {
        return uuid;
    }
    public CaracteristicaStatus getStatusCode() {
        return statusCode;
    }
    public String getNome() {
        return nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public Produto getProduto() {
        return produto;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Caracteristica that = (Caracteristica) o;

        return Objects.equals(uuid, that.uuid) &&
                statusCode == that.statusCode &&
                Objects.equals(nome, that.nome) &&
                Objects.equals(descricao, that.descricao) &&
                Objects.equals(produto, that.produto);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                nome,
                descricao,
                produto);
    }
}
