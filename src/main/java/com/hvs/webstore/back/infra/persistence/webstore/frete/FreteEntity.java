package com.hvs.webstore.back.infra.persistence.webstore.frete;

import com.hvs.webstore.back.domain.entity.webstore.frete.Frete;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "frete")
public class FreteEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;
    private String cepOrigem;
    private String cepDestino;
    private String tipoFreteDesc;
    private Double peso;
    private Double comprimento;
    private Double largura;
    private Double altura;
    private Long valorFrete;
    private Integer prazoDias;
    private Instant criadoEm;

    public FreteEntity() {

    }

    public FreteEntity(final Long id,
                       final String uuid,
                       final String statusDesc,
                       final String cepOrigem,
                       final String cepDestino,
                       final String tipoFreteDesc,
                       final Double peso,
                       final Double comprimento,
                       final Double largura,
                       final Double altura,
                       final Long valorFrete,
                       final Integer prazoDias,
                       final Instant criadoEm) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.cepOrigem = cepOrigem;
        this.cepDestino = cepDestino;
        this.tipoFreteDesc = tipoFreteDesc;
        this.peso = peso;
        this.comprimento = comprimento;
        this.largura = largura;
        this.altura = altura;
        this.valorFrete = valorFrete;
        this.prazoDias = prazoDias;
        this.criadoEm = criadoEm;
    }

    public static FreteEntity from(final Frete aFrete) {

        return new FreteEntity(
                aFrete.getId().getValue() < 0 ? null : aFrete.getId().getValue(),
                aFrete.getUuid().getValue(),
                aFrete.getStatusCode().getDesc(),
                aFrete.getCepOrigem(),
                aFrete.getCepDestino(),
                aFrete.getTipoFrete().getDesc(),
                aFrete.getPeso(),
                aFrete.getComprimento(),
                aFrete.getLargura(),
                aFrete.getAltura(),
                aFrete.getValorFrete(),
                aFrete.getPrazoDias(),
                aFrete.getCriadoEm()
        );
    }

    public static FreteEntity from(final Long aFreteId) {

        final var frete = new FreteEntity();
        frete.setId(aFreteId);

        return frete;
    }

    public Frete toDomain() {

        return Frete.from(
                getId(),
                uuid,
                statusDesc,
                cepOrigem,
                cepDestino,
                tipoFreteDesc,
                peso,
                comprimento,
                largura,
                altura,
                valorFrete,
                prazoDias,
                criadoEm
        );
    }

    public Frete toDomainChildren() {

        return Frete.from(
                getId(),
                uuid,
                statusDesc,
                cepOrigem,
                cepDestino,
                tipoFreteDesc,
                peso,
                comprimento,
                largura,
                altura,
                valorFrete,
                prazoDias,
                criadoEm
        );
    }

    public Frete toDomainSimple() {

        return Frete.from(
                getId(),
                uuid,
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
                null
        );
    }

    @Override
    public Long getId() {
        return id;
    }
    public void setId(final Long id) {
        this.id = id;
    }
    public void setStatusDesc(final String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
