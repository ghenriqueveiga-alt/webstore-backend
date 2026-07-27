package com.hvs.webstore.back.domain.entity.webstore.preco;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.webstore.formapagamento.TipoPagamento;
import com.hvs.webstore.back.domain.entity.webstore.produto.Produto;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.util.Objects;

public class Preco extends Entity<PrecoId> {

    private final PrecoUuid uuid;
    private final PrecoStatus statusCode;
    private final Long valor;
    private final TipoPagamento tipoPagamento;
    private final Integer qtdVezesParcelamento;
    private final Long valorParcela;
    private final Long valorTotalParcelamento;
    private final Produto produto;

    private Preco(final PrecoId id,
                  final PrecoUuid uuid,
                  final PrecoStatus statusCode,
                  final Long valor,
                  final TipoPagamento tipoPagamento,
                  final Integer qtdVezesParcelamento,
                  final Long valorParcela,
                  final Long valorTotalParcelamento,
                  final Produto produto) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.valor = valor;
        this.tipoPagamento = tipoPagamento;
        this.qtdVezesParcelamento = qtdVezesParcelamento;
        this.valorParcela = valorParcela;
        this.valorTotalParcelamento = valorTotalParcelamento;
        this.produto = produto;
    }

    public static Preco create(final Long aValor,
                                final String aTipoPagamento,
                                final Integer aQtdVezesParcelamento,
                                final Long aValorParcela,
                                final Long aValorTotalParcelamento,
                                final Long aProduto) {

        final var id = PrecoId.from(-1L);
        final var uuid = PrecoUuid.unique();
        final var status = PrecoStatus.ACTIVE;
        final var tipoPagamento = aTipoPagamento != null ? TipoPagamento.findByCode(aTipoPagamento) : null;
        final var produto = aProduto != null ? Produto.from(aProduto) : null;

        return new Preco(
                id,
                uuid,
                status,
                aValor,
                tipoPagamento,
                aQtdVezesParcelamento,
                aValorParcela,
                aValorTotalParcelamento,
                produto);
    }

    public static Preco update(final Long aId,
                                final String aUuid,
                                final String aStatusCode,
                                final Long aValor,
                                final String aTipoPagamento,
                                final Integer aQtdVezesParcelamento,
                                final Long aValorParcela,
                                final Long aValorTotalParcelamento,
                                final Long aProduto) {

        final var id = aId != null ? PrecoId.from(aId) : null;
        final var uuid = aUuid != null ? PrecoUuid.from(aUuid) : null;
        final var status = aStatusCode != null ? PrecoStatus.findByCode(aStatusCode) : null;
        final var tipoPagamento = aTipoPagamento != null ? TipoPagamento.findByCode(aTipoPagamento) : null;
        final var produto = aProduto != null ? Produto.from(aProduto) : null;

        return new Preco(
                id,
                uuid,
                status,
                aValor,
                tipoPagamento,
                aQtdVezesParcelamento,
                aValorParcela,
                aValorTotalParcelamento,
                produto);
    }

    public static Preco patch(final String aStatusCode,
                               final Long aValor,
                               final String aTipoPagamento,
                               final Integer aQtdVezesParcelamento,
                               final Long aValorParcela,
                               final Long aValorTotalParcelamento,
                               final Long aProduto,
                               final Preco aExisting) {

        final var id = aExisting.getId();
        final var uuid = aExisting.getUuid();
        final var status = aStatusCode != null ? PrecoStatus.findByCode(aStatusCode) : aExisting.getStatusCode();
        final var valor = aValor != null ? aValor : aExisting.getValor();
        final var tipoPagamento = aTipoPagamento != null ? TipoPagamento.findByCode(aTipoPagamento) : aExisting.getTipoPagamento();
        final var qtdVezesParcelamento = aQtdVezesParcelamento != null ? aQtdVezesParcelamento : aExisting.getQtdVezesParcelamento();
        final var valorParcela = aValorParcela != null ? aValorParcela : aExisting.getValorParcela();
        final var valorTotalParcelamento = aValorTotalParcelamento != null ? aValorTotalParcelamento : aExisting.getValorTotalParcelamento();
        final var produto = aProduto != null ? Produto.from(aProduto) : aExisting.getProduto();

        return new Preco(
                id,
                uuid,
                status,
                valor,
                tipoPagamento,
                qtdVezesParcelamento,
                valorParcela,
                valorTotalParcelamento,
                produto);
    }

    public static Preco from(final Long aId,
                              final String aUuid,
                              final String aStatusDesc,
                              final Long aValor,
                              final String aTipoPagamentoDesc,
                              final Integer aQtdVezesParcelamento,
                              final Long aValorParcela,
                              final Long aValorTotalParcelamento,
                              final Produto aProduto) {

        final var id = aId != null ? PrecoId.from(aId) : null;
        final var uuid = aUuid != null ? PrecoUuid.from(aUuid) : null;
        final var status = aStatusDesc != null ? PrecoStatus.findByDesc(aStatusDesc) : null;
        final var tipoPagamento = aTipoPagamentoDesc != null ? TipoPagamento.findByDesc(aTipoPagamentoDesc) : null;

        return new Preco(
                id,
                uuid,
                status,
                aValor,
                tipoPagamento,
                aQtdVezesParcelamento,
                aValorParcela,
                aValorTotalParcelamento,
                aProduto);
    }

    public static Preco from(final Long aId) {

        return new Preco(
                aId != null ? PrecoId.from(aId) : null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public static Preco from(final String aUuid) {

        return new Preco(
                null,
                aUuid != null ? PrecoUuid.from(aUuid) : null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new PrecoValidator(aHandler, this).validate();
    }

    public PrecoUuid getUuid() {
        return uuid;
    }
    public PrecoStatus getStatusCode() {
        return statusCode;
    }
    public Long getValor() {
        return valor;
    }
    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }
    public Integer getQtdVezesParcelamento() {
        return qtdVezesParcelamento;
    }
    public Long getValorParcela() {
        return valorParcela;
    }
    public Long getValorTotalParcelamento() {
        return valorTotalParcelamento;
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

        Preco preco = (Preco) o;

        return Objects.equals(uuid, preco.uuid) &&
                statusCode == preco.statusCode &&
                Objects.equals(valor, preco.valor) &&
                tipoPagamento == preco.tipoPagamento &&
                Objects.equals(qtdVezesParcelamento, preco.qtdVezesParcelamento) &&
                Objects.equals(valorParcela, preco.valorParcela) &&
                Objects.equals(valorTotalParcelamento, preco.valorTotalParcelamento) &&
                Objects.equals(produto, preco.produto);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                valor,
                tipoPagamento,
                qtdVezesParcelamento,
                valorParcela,
                valorTotalParcelamento,
                produto);
    }
}
