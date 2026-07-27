package com.hvs.webstore.back.infra.persistence.webstore.listadesejos;

import com.hvs.webstore.back.domain.entity.webstore.listadesejos.ItemListaDesejos;
import com.hvs.webstore.back.domain.entity.webstore.listadesejos.ListaDesejos;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.webstore.usuario.UsuarioEntity;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lista_desejos")
public class ListaDesejosEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;
    private Instant criadoEm;
    private Instant atualizadoEm;

    @OneToMany(mappedBy = "listaDesejos", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ItemListaDesejosEntity> items = new ArrayList<>();

    public ListaDesejosEntity() {

    }

    public ListaDesejosEntity(final Long id,
                              final String uuid,
                              final String statusDesc,
                              final UsuarioEntity usuario,
                              final Instant criadoEm,
                              final Instant atualizadoEm) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.usuario = usuario;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
    }

    public static ListaDesejosEntity from(final ListaDesejos aListaDesejos) {

        final var entity = new ListaDesejosEntity(
                aListaDesejos.getId().getValue() < 0 ? null : aListaDesejos.getId().getValue(),
                aListaDesejos.getUuid().getValue(),
                aListaDesejos.getStatusCode().getDesc(),
                aListaDesejos.getUsuario() != null ? UsuarioEntity.from(aListaDesejos.getUsuario()) : null,
                aListaDesejos.getCriadoEm(),
                aListaDesejos.getAtualizadoEm());

        if (aListaDesejos.getItems() != null) {
            for (ItemListaDesejos item : aListaDesejos.getItems()) {
                entity.items.add(ItemListaDesejosEntity.from(item, entity));
            }
        }

        return entity;
    }

    public static ListaDesejosEntity from(final Long aListaDesejosId) {

        final var listaDesejos = new ListaDesejosEntity();
        listaDesejos.setId(aListaDesejosId);

        return listaDesejos;
    }

    public ListaDesejos toDomain() {

        final List<ItemListaDesejos> domainItems = new ArrayList<>();

        if (this.items != null) {
            for (final ItemListaDesejosEntity itemEntity : this.items) {
                domainItems.add(itemEntity.toDomainChildren());
            }
        }

        return ListaDesejos.from(
                getId(),
                uuid,
                statusDesc,
                usuario != null ? usuario.toDomainChildren() : null,
                domainItems,
                criadoEm,
                atualizadoEm);
    }

    public ListaDesejos toDomainChildren() {

        final List<ItemListaDesejos> domainItems = new ArrayList<>();

        if (this.items != null) {
            for (final ItemListaDesejosEntity itemEntity : this.items) {
                domainItems.add(itemEntity.toDomainSimple());
            }
        }

        return ListaDesejos.from(
                getId(),
                uuid,
                statusDesc,
                usuario != null ? usuario.toDomainSimple() : null,
                domainItems,
                criadoEm,
                atualizadoEm);
    }

    public ListaDesejos toDomainSimple() {

        return ListaDesejos.from(
                getId(),
                uuid,
                null,
                null,
                null,
                null,
                null);
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
