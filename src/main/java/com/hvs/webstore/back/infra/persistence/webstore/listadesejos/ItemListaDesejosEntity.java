package com.hvs.webstore.back.infra.persistence.webstore.listadesejos;

import com.hvs.webstore.back.domain.entity.webstore.listadesejos.ItemListaDesejos;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.webstore.produto.ProdutoEntity;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "item_lista_desejos")
public class ItemListaDesejosEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoEntity produto;
    private Instant adicionadoEm;

    @ManyToOne
    @JoinColumn(name = "lista_desejos_id")
    private ListaDesejosEntity listaDesejos;

    public ItemListaDesejosEntity() {

    }

    public ItemListaDesejosEntity(final Long id,
                                  final String uuid,
                                  final String statusDesc,
                                  final ProdutoEntity produto,
                                  final Instant adicionadoEm,
                                  final ListaDesejosEntity listaDesejos) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.produto = produto;
        this.adicionadoEm = adicionadoEm;
        this.listaDesejos = listaDesejos;
    }

    public static ItemListaDesejosEntity from(final ItemListaDesejos aItem,
                                              final ListaDesejosEntity listaDesejos) {

        return new ItemListaDesejosEntity(
                aItem.getId().getValue() < 0 ? null : aItem.getId().getValue(),
                aItem.getUuid().getValue(),
                aItem.getStatusCode().getDesc(),
                aItem.getProduto() != null ? ProdutoEntity.from(aItem.getProduto()) : null,
                aItem.getAdicionadoEm(),
                listaDesejos);
    }

    public static ItemListaDesejosEntity from(final Long aItemListaDesejosId) {

        final var entity = new ItemListaDesejosEntity();
        entity.setId(aItemListaDesejosId);

        return entity;
    }

    public ItemListaDesejos toDomain() {

        return ItemListaDesejos.from(
                getId(),
                uuid,
                statusDesc,
                this.listaDesejos != null ? this.listaDesejos.toDomainChildren() : null,
                this.produto != null ? this.produto.toDomainChildren() : null,
                adicionadoEm
        );
    }

    public ItemListaDesejos toDomainChildren() {

        return ItemListaDesejos.from(
                getId(),
                uuid,
                statusDesc,
                this.listaDesejos != null ? this.listaDesejos.toDomainSimple() : null,
                this.produto != null ? this.produto.toDomainSimple() : null,
                adicionadoEm
        );
    }

    public ItemListaDesejos toDomainSimple() {

        return ItemListaDesejos.from(
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
