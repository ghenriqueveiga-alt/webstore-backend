package com.hvs.webstore.back.infra.persistence.webstore.marca;

import com.hvs.webstore.back.domain.entity.webstore.marca.Marca;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.webstore.produto.ProdutoEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "marca")
public class MarcaEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;
    private String nome;
    private String descricao;

    @OneToMany(mappedBy = "marca")
    private List<ProdutoEntity> produtos;

    public MarcaEntity() {

    }

    public MarcaEntity(final Long id,
                       final String uuid,
                       final String statusDesc,
                       final String nome,
                       final String descricao,
                       final List<ProdutoEntity> produtos) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.nome = nome;
        this.descricao = descricao;
        this.produtos = produtos;
    }

    public static MarcaEntity from(Marca aMarca) {

        return new MarcaEntity(
                aMarca.getId().getValue() < 0 ? null : aMarca.getId().getValue(),
                aMarca.getUuid().getValue(),
                aMarca.getStatusCode().getDesc(),
                aMarca.getNome(),
                aMarca.getDescricao(),
                aMarca.getProdutos() != null ? aMarca.getProdutos().stream()
                        .map(p -> ProdutoEntity.from(p.getId().getValue())).toList() : null
        );
    }

    public static MarcaEntity from(final Long aMarcaId) {

        final var marca = new MarcaEntity();
        marca.setId(aMarcaId);

        return marca;
    }

    public Marca toDomain() {

        return Marca.from(
                getId(),
                uuid,
                statusDesc,
                nome,
                descricao,
                produtos != null ? produtos.stream()
                        .map(ProdutoEntity::toDomainChildren).toList() : null
        );
    }

    public Marca toDomainChildren() {

        return Marca.from(
                getId(),
                uuid,
                statusDesc,
                nome,
                descricao,
                produtos != null ? produtos.stream()
                        .map(ProdutoEntity::toDomainSimple).toList() : null
        );
    }

    public Marca toDomainSimple() {

        return Marca.from(
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
    public void setId(final Long id) {
        this.id = id;
    }
    public void setStatusDesc(final String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
