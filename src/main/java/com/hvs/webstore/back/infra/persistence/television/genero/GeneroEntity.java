package com.hvs.webstore.back.infra.persistence.television.genero;

import com.hvs.webstore.back.domain.entity.television.genero.Genero;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "genero")
public class GeneroEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;
    private String nome;
    private String descricao;

    public GeneroEntity() {

    }

    public GeneroEntity(final Long id,
                        final String uuid,
                        final String statusDesc,
                        final String nome,
                        final String descricao) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.nome = nome;
        this.descricao = descricao;
    }

    public static GeneroEntity from(final Genero aGenero) {

        return new GeneroEntity(
                aGenero.getId().getValue() < 0 ? null : aGenero.getId().getValue(),
                aGenero.getUuid().getValue(),
                aGenero.getStatus().getDesc(),
                aGenero.getNome(),
                aGenero.getDescricao());
    }

    public static GeneroEntity from(final Long aGeneroId) {

        final var genero = new GeneroEntity();
        genero.setId(aGeneroId);

        return genero;
    }

    public Genero toDomain() {

        return Genero.from(getId(),
                           uuid,
                           statusDesc,
                           nome,
                           descricao);
    }

    public Genero toDomainChildren() {

        return Genero.from(getId(),
                           uuid,
                           statusDesc,
                           nome,
                           descricao);
    }

    public Genero toDomainSimple() {

        return Genero.from(getId(),
                           uuid,
                           null,
                           null,
                           null);
    }

    @Override
    public Long getId() {
        return this.id;
    }
    public void setId(final Long aId) {
        this.id = aId;
    }
    public void setStatusDesc(final String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
