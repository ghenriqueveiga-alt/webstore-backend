package com.hvs.webstore.back.infra.persistence.webstore.caracteristica;

import com.hvs.webstore.back.domain.entity.webstore.caracteristica.Caracteristica;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.webstore.produto.ProdutoEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "caracteristica")
public class CaracteristicaEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;
    private String nome;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoEntity produto;

    public CaracteristicaEntity() {

    }

    public CaracteristicaEntity(final Long id,
                                final String uuid,
                                final String statusDesc,
                                final String nome,
                                final String descricao,
                                final ProdutoEntity produto) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }

    public static CaracteristicaEntity from(final Caracteristica aCaracteristica) {

        return new CaracteristicaEntity(
                aCaracteristica.getId().getValue() < 0 ? null : aCaracteristica.getId().getValue(),
                aCaracteristica.getUuid().getValue(),
                aCaracteristica.getStatusCode().getDesc(),
                aCaracteristica.getNome(),
                aCaracteristica.getDescricao(),
                ProdutoEntity.from(aCaracteristica.getProduto())
        );
    }

    public static CaracteristicaEntity from(final Long aCaracteristicaID) {

        final var caracteristica = new CaracteristicaEntity();
        caracteristica.setId(aCaracteristicaID);

        return caracteristica;
    }

    public Caracteristica toDomain() {

        return Caracteristica.from(
                getId(),
                uuid,
                statusDesc,
                nome,
                descricao,
                produto != null ? produto.toDomainChildren() : null
        );
    }

    public Caracteristica toDomainChildren() {

        return Caracteristica.from(
                getId(),
                uuid,
                statusDesc,
                nome,
                descricao,
                produto != null ? produto.toDomainSimple() : null
        );
    }

    public Caracteristica toDomainSimple() {

        return Caracteristica.from(
                getId(),
                uuid,
                null,
                nome,
                null,
                null
        );
    }

    @Override
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
