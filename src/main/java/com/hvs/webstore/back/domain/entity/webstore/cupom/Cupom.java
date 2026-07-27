package com.hvs.webstore.back.domain.entity.webstore.cupom;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.Objects;

public class Cupom extends Entity<CupomId> {

    private final CupomUuid uuid;
    private final CupomStatus statusCode;
    private final String codigo;
    private final TipoDesconto tipoDesconto;
    private final Long valorDesconto;
    private final Long valorMinimo;
    private final Integer quantidadeMaxima;
    private final Integer usosAtuais;
    private final Instant dataExpiracao;
    private final Instant criadoEm;
    private final Boolean ativo;

    private Cupom(final CupomId id,
                  final CupomUuid uuid,
                  final CupomStatus statusCode,
                  final String codigo,
                  final TipoDesconto tipoDesconto,
                  final Long valorDesconto,
                  final Long valorMinimo,
                  final Integer quantidadeMaxima,
                  final Integer usosAtuais,
                  final Instant dataExpiracao,
                  final Instant criadoEm,
                  final Boolean ativo) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.codigo = codigo;
        this.tipoDesconto = tipoDesconto;
        this.valorDesconto = valorDesconto;
        this.valorMinimo = valorMinimo;
        this.quantidadeMaxima = quantidadeMaxima;
        this.usosAtuais = usosAtuais;
        this.dataExpiracao = dataExpiracao;
        this.criadoEm = criadoEm;
        this.ativo = ativo;
    }

    public static Cupom create(final String aCodigo,
                               final String aTipoDescontoString,
                               final Long aValorDesconto,
                               final Long aValorMinimo,
                               final Integer aQuantidadeMaxima,
                               final Instant aDataExpiracao) {

        return new Cupom(
                CupomId.from(-1L),
                CupomUuid.unique(),
                CupomStatus.ACTIVE,
                aCodigo,
                aTipoDescontoString != null ? TipoDesconto.findByDesc(aTipoDescontoString) : null,
                aValorDesconto,
                aValorMinimo,
                aQuantidadeMaxima,
                0,
                aDataExpiracao,
                Instant.now(),
                null);
    }

    public static Cupom update(final Long aId,
                               final String aUuid,
                               final String aStatusCode,
                               final String aCodigo,
                               final String aTipoDescontoString,
                               final Long aValorDesconto,
                               final Long aValorMinimo,
                               final Integer aQuantidadeMaxima,
                               final Integer aUsosAtuais,
                               final Instant aDataExpiracao,
                               final Instant aCriadoEm,
                               final Boolean aAtivo) {

        return new Cupom(
                aId != null ? CupomId.from(aId) : null,
                aUuid != null ? CupomUuid.from(aUuid) : null,
                aStatusCode != null ? CupomStatus.findByCode(aStatusCode) : null,
                aCodigo,
                aTipoDescontoString != null ? TipoDesconto.findByCode(aTipoDescontoString) : null,
                aValorDesconto,
                aValorMinimo,
                aQuantidadeMaxima,
                aUsosAtuais,
                aDataExpiracao,
                aCriadoEm,
                aAtivo);
    }

    public static Cupom patch(final String aStatusCode,
                              final String aCodigo,
                              final String aTipoDescontoString,
                              final Long aValorDesconto,
                              final Long aValorMinimo,
                              final Integer aQuantidadeMaxima,
                              final Integer aUsosAtuais,
                              final Instant aDataExpiracao,
                              final Boolean aAtivo,
                              final Cupom aExisting) {

        return new Cupom(
                aExisting.getId(),
                aExisting.getUuid(),
                aStatusCode != null ? CupomStatus.findByCode(aStatusCode) : aExisting.getStatusCode(),
                aCodigo != null ? aCodigo : aExisting.getCodigo(),
                aTipoDescontoString != null ? TipoDesconto.findByCode(aTipoDescontoString) : aExisting.getTipoDesconto(),
                aValorDesconto != null ? aValorDesconto : aExisting.getValorDesconto(),
                aValorMinimo != null ? aValorMinimo : aExisting.getValorMinimo(),
                aQuantidadeMaxima != null ? aQuantidadeMaxima : aExisting.getQuantidadeMaxima(),
                aUsosAtuais != null ? aUsosAtuais : aExisting.getUsosAtuais(),
                aDataExpiracao != null ? aDataExpiracao : aExisting.getDataExpiracao(),
                aExisting.getCriadoEm(),
                aAtivo != null ? aAtivo : aExisting.getAtivo());
    }

    public static Cupom from(final Long aId,
                             final String aUuid,
                             final String aStatusDesc,
                             final String aCodigo,
                             final String aTipoDescontoString,
                             final Long aValorDesconto,
                             final Long aValorMinimo,
                             final Integer aQuantidadeMaxima,
                             final Integer aUsosAtuais,
                             final Instant aDataExpiracao,
                             final Instant aCriadoEm,
                             final Boolean aAtivo) {

        return new Cupom(
                aId != null ? CupomId.from(aId) : null,
                aUuid != null ? CupomUuid.from(aUuid) : null,
                aStatusDesc != null ? CupomStatus.findByDesc(aStatusDesc) : null,
                aCodigo,
                aTipoDescontoString != null ? TipoDesconto.findByDesc(aTipoDescontoString) : null,
                aValorDesconto,
                aValorMinimo,
                aQuantidadeMaxima,
                aUsosAtuais,
                aDataExpiracao,
                aCriadoEm,
                aAtivo);
    }

    public static Cupom from(final Long aId) {

        return new Cupom(
                aId != null ? CupomId.from(aId) : null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public static Cupom from(final String aUuid) {

        return new Cupom(
                null,
                aUuid != null ? CupomUuid.from(aUuid) : null,
                null,
                null,
                null,
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

        new CupomValidator(aHandler, this).validate();
    }

    public CupomUuid getUuid() {
        return uuid;
    }
    public CupomStatus getStatusCode() {
        return statusCode;
    }
    public String getCodigo() {
        return codigo;
    }
    public TipoDesconto getTipoDesconto() {
        return tipoDesconto;
    }
    public Long getValorDesconto() {
        return valorDesconto;
    }
    public Long getValorMinimo() {
        return valorMinimo;
    }
    public Integer getQuantidadeMaxima() {
        return quantidadeMaxima;
    }
    public Integer getUsosAtuais() {
        return usosAtuais;
    }
    public Instant getDataExpiracao() {
        return dataExpiracao;
    }
    public Instant getCriadoEm() {
        return criadoEm;
    }
    public Boolean getAtivo() {
        return ativo;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Cupom cupom = (Cupom) o;

        return Objects.equals(uuid, cupom.uuid) &&
                statusCode == cupom.statusCode &&
                Objects.equals(codigo, cupom.codigo) &&
                tipoDesconto == cupom.tipoDesconto &&
                Objects.equals(valorDesconto, cupom.valorDesconto) &&
                Objects.equals(valorMinimo, cupom.valorMinimo) &&
                Objects.equals(quantidadeMaxima, cupom.quantidadeMaxima) &&
                Objects.equals(usosAtuais, cupom.usosAtuais) &&
                Objects.equals(dataExpiracao, cupom.dataExpiracao) &&
                Objects.equals(criadoEm, cupom.criadoEm) &&
                Objects.equals(ativo, cupom.ativo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                codigo,
                tipoDesconto,
                valorDesconto,
                valorMinimo,
                quantidadeMaxima,
                usosAtuais,
                dataExpiracao,
                criadoEm,
                ativo);
    }
}
