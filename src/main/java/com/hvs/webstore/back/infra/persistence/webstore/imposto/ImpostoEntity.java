package com.hvs.webstore.back.infra.persistence.webstore.imposto;

import com.hvs.webstore.back.domain.entity.webstore.imposto.Imposto;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "imposto")
public class ImpostoEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;
    private String nome;
    private String tipoDesc;
    private Integer aliquota;
    private String descricao;

    public ImpostoEntity() {

    }

    public ImpostoEntity(final Long id,
                         final String uuid,
                         final String statusDesc,
                         final String nome,
                         final String tipoCode,
                         final Integer aliquota,
                         final String descricao) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.nome = nome;
        this.tipoDesc = tipoDesc;
        this.aliquota = aliquota;
        this.descricao = descricao;
    }

    public static ImpostoEntity from(final Imposto aImposto) {

        return new ImpostoEntity(
                aImposto.getId().getValue() < 0 ? null : aImposto.getId().getValue(),
                aImposto.getUuid().getValue(),
                aImposto.getStatusCode().getDesc(),
                aImposto.getNome(),
                aImposto.getTipoImposto().getDesc(),
                aImposto.getAliquota(),
                aImposto.getDescricao()
        );
    }

    public static ImpostoEntity from(final Long aImpostoId) {

        final var imposto = new ImpostoEntity();
        imposto.setId(aImpostoId);

        return imposto;
    }

    public Imposto toDomain() {

        return Imposto.from(
                getId(),
                uuid,
                statusDesc,
                nome,
                tipoDesc,
                aliquota,
                descricao
        );
    }

    public Imposto toDomainChildren() {

        return Imposto.from(
                getId(),
                uuid,
                statusDesc,
                nome,
                tipoDesc,
                aliquota,
                descricao
        );
    }

    public Imposto toDomainSimple() {

        return Imposto.from(
                getId(),
                uuid,
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
