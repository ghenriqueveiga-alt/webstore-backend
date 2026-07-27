package com.hvs.webstore.back.infra.persistence.webstore.categoriahierarquia;

import com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia.CategoriaHierarquia;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.webstore.categoria.CategoriaEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "categoria_hierarquia")
public class CategoriaHierarquiaEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaEntity categoria;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private CategoriaEntity categoriaPai;
    private Integer nivel;

    public CategoriaHierarquiaEntity() {

    }

    public CategoriaHierarquiaEntity(final Long id,
                                     final String uuid,
                                     final String statusDesc,
                                     final CategoriaEntity categoria,
                                     final CategoriaEntity categoriaPai,
                                     final Integer nivel) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.categoria = categoria;
        this.categoriaPai = categoriaPai;
        this.nivel = nivel;
    }

    public static CategoriaHierarquiaEntity from(final CategoriaHierarquia aCategoriaHierarquia) {

        return new CategoriaHierarquiaEntity(
                aCategoriaHierarquia.getId().getValue() < 0 ? null : aCategoriaHierarquia.getId().getValue(),
                aCategoriaHierarquia.getUuid().getValue(),
                aCategoriaHierarquia.getStatusCode().getDesc(),
                aCategoriaHierarquia.getCategoria() != null ? CategoriaEntity.from(aCategoriaHierarquia.getCategoria().getId().getValue()) : null,
                aCategoriaHierarquia.getCategoriaPai() != null ? CategoriaEntity.from(aCategoriaHierarquia.getCategoriaPai().getId().getValue()) : null,
                aCategoriaHierarquia.getNivel()
        );
    }

    public static CategoriaHierarquiaEntity from(final Long aCategoriaHierarquiaId) {

        final var categoriaHierarquia = new CategoriaHierarquiaEntity();
        categoriaHierarquia.setId(aCategoriaHierarquiaId);

        return categoriaHierarquia;
    }

    public CategoriaHierarquia toDomain() {

        return CategoriaHierarquia.from(
                getId(),
                uuid,
                statusDesc,
                categoria != null ? categoria.toDomainChildren() : null,
                categoriaPai != null ? categoriaPai.toDomainChildren() : null,
                nivel
        );
    }

    public CategoriaHierarquia toDomainChildren() {

        return CategoriaHierarquia.from(
                getId(),
                uuid,
                statusDesc,
                categoria != null ? categoria.toDomainSimple() : null,
                categoriaPai != null ? categoriaPai.toDomainSimple() : null,
                nivel
        );
    }

    public CategoriaHierarquia toDomainSimple() {

        return CategoriaHierarquia.from(
                getId(),
                uuid,
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
