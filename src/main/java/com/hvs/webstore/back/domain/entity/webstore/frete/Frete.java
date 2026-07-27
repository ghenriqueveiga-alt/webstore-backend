package com.hvs.webstore.back.domain.entity.webstore.frete;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.Objects;

public class Frete extends Entity<FreteId> {

    private final FreteUuid uuid;
    private final FreteStatus statusCode;
    private final String cepOrigem;
    private final String cepDestino;
    private final TipoFrete tipoFrete;
    private final Double peso;
    private final Double comprimento;
    private final Double largura;
    private final Double altura;
    private final Long valorFrete;
    private final Integer prazoDias;
    private final Instant criadoEm;

    private Frete(final FreteId id,
                  final FreteUuid uuid,
                  final FreteStatus statusCode,
                  final String cepOrigem,
                  final String cepDestino,
                  final TipoFrete tipoFrete,
                  final Double peso,
                  final Double comprimento,
                  final Double largura,
                  final Double altura,
                  final Long valorFrete,
                  final Integer prazoDias,
                  final Instant criadoEm) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.cepOrigem = cepOrigem;
        this.cepDestino = cepDestino;
        this.tipoFrete = tipoFrete;
        this.peso = peso;
        this.comprimento = comprimento;
        this.largura = largura;
        this.altura = altura;
        this.valorFrete = valorFrete;
        this.prazoDias = prazoDias;
        this.criadoEm = criadoEm;
    }

    public static Frete create(final String aCepOrigem,
                               final String aCepDestino,
                               final TipoFrete aTipoFrete,
                               final Double aPeso,
                               final Double aComprimento,
                               final Double aLargura,
                               final Double aAltura) {

        return new Frete(
                FreteId.from(-1L),
                FreteUuid.unique(),
                FreteStatus.ACTIVE,
                aCepOrigem,
                aCepDestino,
                aTipoFrete,
                aPeso,
                aComprimento,
                aLargura,
                aAltura,
                null,
                null,
                Instant.now());
    }

    public static Frete createFrete(final String aCepOrigem,
                                    final String aCepDestino,
                                    final String aTipoFreteString,
                                    final Double aPeso,
                                    final Double aComprimento,
                                    final Double aLargura,
                                    final Double aAltura,
                                    final Long aValorFrete,
                                    final Integer aPrazoDias) {

        return new Frete(
                FreteId.from(-1L),
                FreteUuid.unique(),
                FreteStatus.ACTIVE,
                aCepOrigem,
                aCepDestino,
                aTipoFreteString != null ? TipoFrete.findByCode(aTipoFreteString) : null,
                aPeso,
                aComprimento,
                aLargura,
                aAltura,
                aValorFrete,
                aPrazoDias,
                Instant.now());
    }

    public static Frete updateFrete(final Long aId,
                                    final String aUuid,
                                    final String aStatusCode,
                                    final String aCepOrigem,
                                    final String aCepDestino,
                                    final String aTipoFreteString,
                                    final Double aPeso,
                                    final Double aComprimento,
                                    final Double aLargura,
                                    final Double aAltura,
                                    final Long aValorFrete,
                                    final Integer aPrazoDias,
                                    final Instant aCriadoEm) {

        return new Frete(
                aId != null ? FreteId.from(aId) : null,
                aUuid != null ? FreteUuid.from(aUuid) : null,
                aStatusCode != null ? FreteStatus.findByCode(aStatusCode) : null,
                aCepOrigem,
                aCepDestino,
                aTipoFreteString != null ? TipoFrete.findByCode(aTipoFreteString) : null,
                aPeso,
                aComprimento,
                aLargura,
                aAltura,
                aValorFrete,
                aPrazoDias,
                aCriadoEm);
    }

    public static Frete patchFrete(final String aStatusCode,
                                   final String aCepOrigem,
                                   final String aCepDestino,
                                   final String aTipoFreteString,
                                   final Double aPeso,
                                   final Double aComprimento,
                                   final Double aLargura,
                                   final Double aAltura,
                                   final Long aValorFrete,
                                   final Integer aPrazoDias,
                                   final Frete aFreteDb) {

        return new Frete(
                aFreteDb.getId(),
                aFreteDb.getUuid(),
                aStatusCode != null ? FreteStatus.findByCode(aStatusCode) : aFreteDb.getStatusCode(),
                aCepOrigem != null ? aCepOrigem : aFreteDb.getCepOrigem(),
                aCepDestino != null ? aCepDestino : aFreteDb.getCepDestino(),
                aTipoFreteString != null ? TipoFrete.findByCode(aTipoFreteString) : aFreteDb.getTipoFrete(),
                aPeso != null ? aPeso : aFreteDb.getPeso(),
                aComprimento != null ? aComprimento : aFreteDb.getComprimento(),
                aLargura != null ? aLargura : aFreteDb.getLargura(),
                aAltura != null ? aAltura : aFreteDb.getAltura(),
                aValorFrete != null ? aValorFrete : aFreteDb.getValorFrete(),
                aPrazoDias != null ? aPrazoDias : aFreteDb.getPrazoDias(),
                aFreteDb.getCriadoEm());
    }

    public static Frete from(final Long aId,
                             final String aUuid,
                             final String aStatusCode,
                             final String aCepOrigem,
                             final String aCepDestino,
                             final String aTipoFreteString,
                             final Double aPeso,
                             final Double aComprimento,
                             final Double aLargura,
                             final Double aAltura,
                             final Long aValorFrete,
                             final Integer aPrazoDias,
                             final Instant aCriadoEm) {

        return new Frete(
                aId != null ? FreteId.from(aId) : null,
                aUuid != null ? FreteUuid.from(aUuid) : null,
                aStatusCode != null ? FreteStatus.findByCode(aStatusCode) : null,
                aCepOrigem,
                aCepDestino,
                aTipoFreteString != null ? TipoFrete.findByCode(aTipoFreteString) : null,
                aPeso,
                aComprimento,
                aLargura,
                aAltura,
                aValorFrete,
                aPrazoDias,
                aCriadoEm);
    }

    public static Frete from(final Long aId) {

        return new Frete(
                aId != null ? FreteId.from(aId) : null,
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
                null,
                null);
    }

    public static Frete from(final String aUuid) {

        return new Frete(
                null,
                aUuid != null ? FreteUuid.from(aUuid) : null,
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

    @Override
    public void validate(ValidationHandler aHandler) {

        new FreteValidator(aHandler, this).validate();
    }

    public FreteUuid getUuid() {
        return uuid;
    }
    public FreteStatus getStatusCode() {
        return statusCode;
    }
    public String getCepOrigem() {
        return cepOrigem;
    }
    public String getCepDestino() {
        return cepDestino;
    }
    public TipoFrete getTipoFrete() {
        return tipoFrete;
    }
    public Double getPeso() {
        return peso;
    }
    public Double getComprimento() {
        return comprimento;
    }
    public Double getLargura() {
        return largura;
    }
    public Double getAltura() {
        return altura;
    }
    public Long getValorFrete() {
        return valorFrete;
    }
    public Integer getPrazoDias() {
        return prazoDias;
    }
    public Instant getCriadoEm() {
        return criadoEm;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Frete frete = (Frete) o;

        return Objects.equals(uuid, frete.uuid) &&
                statusCode == frete.statusCode &&
                Objects.equals(cepOrigem, frete.cepOrigem) &&
                Objects.equals(cepDestino, frete.cepDestino) &&
                tipoFrete == frete.tipoFrete &&
                Objects.equals(peso, frete.peso) &&
                Objects.equals(comprimento, frete.comprimento) &&
                Objects.equals(largura, frete.largura) &&
                Objects.equals(altura, frete.altura) &&
                Objects.equals(valorFrete, frete.valorFrete) &&
                Objects.equals(prazoDias, frete.prazoDias) &&
                Objects.equals(criadoEm, frete.criadoEm);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                cepOrigem,
                cepDestino,
                tipoFrete,
                peso,
                comprimento,
                largura,
                altura,
                valorFrete,
                prazoDias,
                criadoEm);
    }
}
