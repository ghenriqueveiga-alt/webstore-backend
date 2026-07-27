package com.hvs.webstore.back.infra.persistence.webstore.categoria;

import com.hvs.webstore.back.domain.entity.webstore.categoria.Categoria;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.webstore.produto.ProdutoEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categoria")
public class CategoriaEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;
    private String nome;
    private String descricao;

    @OneToMany(mappedBy = "categoria")
    private List<ProdutoEntity> produtos;

    public CategoriaEntity() {

    }

    public CategoriaEntity(final Long id,
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

    public static CategoriaEntity from(final Categoria aCategoria) {

        return new CategoriaEntity(
                aCategoria.getId().getValue() < 0 ? null : aCategoria.getId().getValue(),
                aCategoria.getUuid().getValue(),
                aCategoria.getStatusCode().getDesc(),
                aCategoria.getNome(),
                aCategoria.getDescricao(),
                aCategoria.getProdutos() != null ? aCategoria.getProdutos().stream()
                        .map(p -> ProdutoEntity.from(p.getId().getValue())).toList() : null
        );
    }

    public static CategoriaEntity from(final Long aCategoriaID) {

        final var categoria = new CategoriaEntity();
        categoria.setId(aCategoriaID);

        return categoria;
    }

    public Categoria toDomain() {

        return Categoria.from(
                getId(),
                uuid,
                statusDesc,
                nome,
                descricao,
                produtos != null ? produtos.stream()
                        .map(ProdutoEntity::toDomainChildren).toList() : null
        );
    }

    public Categoria toDomainChildren() {

        return Categoria.from(
                getId(),
                uuid,
                statusDesc,
                nome,
                descricao,
                produtos != null ? produtos.stream()
                        .map(ProdutoEntity::toDomainSimple).toList() : null
        );
    }

    public Categoria toDomainSimple() {

        return Categoria.from(
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
