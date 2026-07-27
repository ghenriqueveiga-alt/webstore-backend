package com.hvs.webstore.back.infra.persistence.webstore.cupom;

import com.hvs.webstore.back.domain.entity.webstore.cupom.Cupom;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "cupom")
public class CupomEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;
    private String codigo;
    private String tipoDescontoCode;
    private Long valorDesconto;
    private Long valorMinimo;
    private Integer quantidadeMaxima;
    private Integer usosAtuais;
    private Instant dataExpiracao;
    private Instant criadoEm;
    private Boolean ativo;

    public CupomEntity() {

    }

    public CupomEntity(final Long id,
                       final String uuid,
                       final String statusDesc,
                       final String codigo,
                       final String tipoDescontoCode,
                       final Long valorDesconto,
                       final Long valorMinimo,
                       final Integer quantidadeMaxima,
                       final Integer usosAtuais,
                       final Instant dataExpiracao,
                       final Instant criadoEm,
                       final Boolean ativo) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.codigo = codigo;
        this.tipoDescontoCode = tipoDescontoCode;
        this.valorDesconto = valorDesconto;
        this.valorMinimo = valorMinimo;
        this.quantidadeMaxima = quantidadeMaxima;
        this.usosAtuais = usosAtuais;
        this.dataExpiracao = dataExpiracao;
        this.criadoEm = criadoEm;
        this.ativo = ativo;
    }

    public static CupomEntity from(final Cupom aCupom) {

        return new CupomEntity(
                aCupom.getId().getValue() < 0 ? null : aCupom.getId().getValue(),
                aCupom.getUuid().getValue(),
                aCupom.getStatusCode().getDesc(),
                aCupom.getCodigo(),
                aCupom.getTipoDesconto().getCode(),
                aCupom.getValorDesconto(),
                aCupom.getValorMinimo(),
                aCupom.getQuantidadeMaxima(),
                aCupom.getUsosAtuais(),
                aCupom.getDataExpiracao(),
                aCupom.getCriadoEm(),
                aCupom.getAtivo()
        );
    }

    public static CupomEntity from(final Long aCupomId) {

        final var cupom = new CupomEntity();
        cupom.setId(aCupomId);

        return cupom;
    }

    public Cupom toDomain() {

        return Cupom.from(
                getId(),
                uuid,
                statusDesc,
                codigo,
                tipoDescontoCode,
                valorDesconto,
                valorMinimo,
                quantidadeMaxima,
                usosAtuais,
                dataExpiracao,
                criadoEm,
                ativo
        );
    }

    public Cupom toDomainChildren() {

        return Cupom.from(
                getId(),
                uuid,
                statusDesc,
                codigo,
                tipoDescontoCode,
                valorDesconto,
                valorMinimo,
                quantidadeMaxima,
                usosAtuais,
                dataExpiracao,
                criadoEm,
                ativo
        );
    }

    public Cupom toDomainSimple() {

        return Cupom.from(
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
