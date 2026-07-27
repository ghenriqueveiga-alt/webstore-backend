package com.hvs.webstore.back.domain.entity.webstore.precopromocional;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.webstore.preco.Preco;
import com.hvs.webstore.back.domain.entity.webstore.produto.Produto;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.Objects;

public class PrecoPromocional extends Entity<PrecoPromocionalId> {

    private final PrecoPromocionalUuid uuid;
    private final PrecoPromocionalStatus statusCode;
    private final Produto produto;
    private final Preco precoPromocional;
    private final Instant dataInicio;
    private final Instant dataFim;

    private PrecoPromocional(final PrecoPromocionalId id,
                              final PrecoPromocionalUuid uuid,
                              final PrecoPromocionalStatus statusCode,
                              final Produto produto,
                              final Preco precoPromocional,
                              final Instant dataInicio,
                              final Instant dataFim) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.produto = produto;
        this.precoPromocional = precoPromocional;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public static PrecoPromocional create(final Long aProdutoId,
                                           final Long aPrecoPromocionalId,
                                           final Instant aDataInicio,
                                           final Instant aDataFim) {

        final var id = PrecoPromocionalId.from(-1L);
        final var uuid = PrecoPromocionalUuid.unique();
        final var status = PrecoPromocionalStatus.ACTIVE;
        final var produto = aProdutoId != null ? Produto.from(aProdutoId) : null;
        final var precoPromocional = aPrecoPromocionalId != null ? Preco.from(aPrecoPromocionalId) : null;

        return new PrecoPromocional(
                id,
                uuid,
                status,
                produto,
                precoPromocional,
                aDataInicio,
                aDataFim);
    }

    public static PrecoPromocional update(final Long aId,
                                           final String aUuid,
                                           final String aStatusCode,
                                           final Long aProdutoId,
                                           final Long aPrecoPromocionalId,
                                           final Instant aDataInicio,
                                           final Instant aDataFim) {

        final var id = aId != null ? PrecoPromocionalId.from(aId) : null;
        final var uuid = aUuid != null ? PrecoPromocionalUuid.from(aUuid) : null;
        final var status = aStatusCode != null ? PrecoPromocionalStatus.findByCode(aStatusCode) : null;
        final var produto = aProdutoId != null ? Produto.from(aProdutoId) : null;
        final var precoPromocional = aPrecoPromocionalId != null ? Preco.from(aPrecoPromocionalId) : null;

        return new PrecoPromocional(
                id,
                uuid,
                status,
                produto,
                precoPromocional,
                aDataInicio,
                aDataFim);
    }

    public static PrecoPromocional patch(final String aStatusCode,
                                          final Long aProdutoId,
                                          final Long aPrecoPromocionalId,
                                          final Instant aDataInicio,
                                          final Instant aDataFim,
                                          final PrecoPromocional aExisting) {

        final var id = aExisting.getId();
        final var uuid = aExisting.getUuid();
        final var status = aStatusCode != null ? PrecoPromocionalStatus.findByCode(aStatusCode) : aExisting.getStatusCode();
        final var produto = aProdutoId != null ? Produto.from(aProdutoId) : aExisting.getProduto();
        final var precoPromocional = aPrecoPromocionalId != null ? Preco.from(aPrecoPromocionalId) : aExisting.getPrecoPromocional();
        final var dataInicio = aDataInicio != null ? aDataInicio : aExisting.getDataInicio();
        final var dataFim = aDataFim != null ? aDataFim : aExisting.getDataFim();

        return new PrecoPromocional(
                id,
                uuid,
                status,
                produto,
                precoPromocional,
                dataInicio,
                dataFim);
    }

    public static PrecoPromocional from(final Long aId,
                                         final String aUuid,
                                         final String aStatusDesc,
                                         final Produto aProduto,
                                         final Preco aPrecoPromocional,
                                         final Instant aDataInicio,
                                         final Instant aDataFim) {

        final var id = aId != null ? PrecoPromocionalId.from(aId) : null;
        final var uuid = aUuid != null ? PrecoPromocionalUuid.from(aUuid) : null;
        final var status = aStatusDesc != null ? PrecoPromocionalStatus.findByDesc(aStatusDesc) : null;

        return new PrecoPromocional(
                id,
                uuid,
                status,
                aProduto,
                aPrecoPromocional,
                aDataInicio,
                aDataFim);
    }

    public static PrecoPromocional from(final Long aId) {

        return new PrecoPromocional(
                aId != null ? PrecoPromocionalId.from(aId) : null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public static PrecoPromocional from(final String aUuid) {

        return new PrecoPromocional(
                null,
                aUuid != null ? PrecoPromocionalUuid.from(aUuid) : null,
                null,
                null,
                null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new PrecoPromocionalValidator(aHandler, this).validate();
    }

    public PrecoPromocionalUuid getUuid() {
        return uuid;
    }
    public PrecoPromocionalStatus getStatusCode() {
        return statusCode;
    }
    public Produto getProduto() {
        return produto;
    }
    public Preco getPrecoPromocional() {
        return precoPromocional;
    }
    public Instant getDataInicio() {
        return dataInicio;
    }
    public Instant getDataFim() {
        return dataFim;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        PrecoPromocional that = (PrecoPromocional) o;

        return Objects.equals(uuid, that.uuid) &&
                statusCode == that.statusCode &&
                Objects.equals(produto, that.produto) &&
                Objects.equals(precoPromocional, that.precoPromocional) &&
                Objects.equals(dataInicio, that.dataInicio) &&
                Objects.equals(dataFim, that.dataFim);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                produto,
                precoPromocional,
                dataInicio,
                dataFim);
    }
}
