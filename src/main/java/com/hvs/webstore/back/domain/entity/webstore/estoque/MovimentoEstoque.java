package com.hvs.webstore.back.domain.entity.webstore.estoque;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.Objects;

public class MovimentoEstoque extends Entity<MovimentoEstoqueId> {

    private final MovimentoEstoqueUuid uuid;
    private final MovimentoEstoqueStatus statusCode;
    private final Estoque estoque;
    private final Integer quantidade;
    private final TipoMovimento tipo;
    private final String observacao;
    private final Instant data;

    private MovimentoEstoque(final MovimentoEstoqueId id,
                             final MovimentoEstoqueUuid uuid,
                             final MovimentoEstoqueStatus statusCode,
                             final Estoque estoque,
                             final Integer quantidade,
                             final TipoMovimento tipo,
                             final String observacao,
                             final Instant data) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.estoque = estoque;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.observacao = observacao;
        this.data = data;
    }

    public static MovimentoEstoque create(final Long aEstoqueId,
                                          final String aTipoString,
                                          final Integer aQuantidade,
                                          final String aObservacao) {

        return new MovimentoEstoque(
                MovimentoEstoqueId.from(-1L),
                MovimentoEstoqueUuid.unique(),
                MovimentoEstoqueStatus.ACTIVE,
                aEstoqueId != null ? Estoque.from(aEstoqueId) : null,
                aQuantidade,
                aTipoString != null ? TipoMovimento.findByCode(aTipoString) : null,
                aObservacao,
                Instant.now());
    }

    public static MovimentoEstoque createMovimentoEstoque(
            final Long aEstoqueId,
            final Integer aQuantidade,
            final String aTipoString,
            final String aObservacao) {

        return new MovimentoEstoque(
                MovimentoEstoqueId.from(-1L),
                MovimentoEstoqueUuid.unique(),
                MovimentoEstoqueStatus.ACTIVE,
                aEstoqueId != null ? Estoque.from(aEstoqueId) : null,
                aQuantidade,
                aTipoString != null ? TipoMovimento.findByCode(aTipoString) : null,
                aObservacao,
                Instant.now());
    }

    public static MovimentoEstoque updateMovimentoEstoque(
            final Long aId,
            final String aUuid,
            final String aStatusCode,
            final Long aEstoqueId,
            final Integer aQuantidade,
            final String aTipoString,
            final String aObservacao,
            final Instant aData) {

        return new MovimentoEstoque(
                aId != null ? MovimentoEstoqueId.from(aId) : null,
                aUuid != null ? MovimentoEstoqueUuid.from(aUuid) : null,
                aStatusCode != null ? MovimentoEstoqueStatus.findByCode(aStatusCode) : null,
                aEstoqueId != null ? Estoque.from(aEstoqueId) : null,
                aQuantidade,
                aTipoString != null ? TipoMovimento.findByCode(aTipoString) : null,
                aObservacao,
                aData);
    }

    public static MovimentoEstoque patchMovimentoEstoque(
            final String aStatusCode,
            final Long aEstoqueId,
            final Integer aQuantidade,
            final String aTipoString,
            final String aObservacao,
            final Instant aData,
            final MovimentoEstoque aMovimentoDb) {

        return new MovimentoEstoque(
                aMovimentoDb.getId(),
                aMovimentoDb.getUuid(),
                aStatusCode != null ? MovimentoEstoqueStatus.findByCode(aStatusCode) : aMovimentoDb.getStatusCode(),
                aEstoqueId != null ? Estoque.from(aEstoqueId) : aMovimentoDb.getEstoque(),
                aQuantidade != null ? aQuantidade : aMovimentoDb.getQuantidade(),
                aTipoString != null ? TipoMovimento.findByCode(aTipoString) : aMovimentoDb.getTipo(),
                aObservacao != null ? aObservacao : aMovimentoDb.getObservacao(),
                aData != null ? aData : aMovimentoDb.getData());
    }

    public static MovimentoEstoque from(final Long aId,
                                        final String aUuid,
                                        final String aStatusCode,
                                        final Estoque aEstoque,
                                        final String aTipoString,
                                        final Integer aQuantidade,
                                        final String aObservacao,
                                        final Instant aData) {

        return new MovimentoEstoque(
                aId != null ? MovimentoEstoqueId.from(aId) : null,
                aUuid != null ? MovimentoEstoqueUuid.from(aUuid) : null,
                aStatusCode != null ? MovimentoEstoqueStatus.findByCode(aStatusCode) : null,
                aEstoque,
                aQuantidade,
                aTipoString != null ? TipoMovimento.findByCode(aTipoString) : null,
                aObservacao,
                aData);
    }

    public static MovimentoEstoque from(final Long aId) {

        return new MovimentoEstoque(
                aId != null ? MovimentoEstoqueId.from(aId) : null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public static MovimentoEstoque from(final String aUuid) {

        return new MovimentoEstoque(
                null,
                aUuid != null ? MovimentoEstoqueUuid.from(aUuid) : null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new MovimentoEstoqueValidator(aHandler, this).validate();
    }

    public MovimentoEstoqueUuid getUuid() {
        return uuid;
    }
    public MovimentoEstoqueStatus getStatusCode() {
        return statusCode;
    }
    public Estoque getEstoque() {
        return estoque;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public TipoMovimento getTipo() {
        return tipo;
    }
    public String getObservacao() {
        return observacao;
    }
    public Instant getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        MovimentoEstoque that = (MovimentoEstoque) o;

        return Objects.equals(uuid, that.uuid) &&
                statusCode == that.statusCode &&
                Objects.equals(estoque, that.estoque) &&
                Objects.equals(quantidade, that.quantidade) &&
                tipo == that.tipo &&
                Objects.equals(observacao, that.observacao) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                estoque,
                quantidade,
                tipo,
                observacao,
                data);
    }
}
