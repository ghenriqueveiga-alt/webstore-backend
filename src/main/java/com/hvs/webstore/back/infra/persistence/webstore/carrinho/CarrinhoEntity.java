package com.hvs.webstore.back.infra.persistence.webstore.carrinho;

import com.hvs.webstore.back.domain.entity.webstore.carrinho.Carrinho;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.webstore.usuario.UsuarioEntity;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "carrinho")
public class CarrinhoEntity extends BasicEntity {

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

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCarrinhoEntity> items;

    public CarrinhoEntity() {

    }

    public CarrinhoEntity(final Long id,
                           final String uuid,
                           final String statusDesc,
                           final UsuarioEntity usuario,
                           final Instant criadoEm,
                           final Instant atualizadoEm,
                           final List<ItemCarrinhoEntity> items) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.usuario = usuario;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
        this.items = items;
    }

    public static CarrinhoEntity from(final Carrinho aCarrinho) {

        List<ItemCarrinhoEntity> itemEntities = null;
        if (aCarrinho.getItems() != null) {
            itemEntities = aCarrinho.getItems().stream()
                    .map(ItemCarrinhoEntity::from)
                    .collect(java.util.stream.Collectors.toList());
        }

        var entity = new CarrinhoEntity(
                aCarrinho.getId().getValue() < 0 ? null : aCarrinho.getId().getValue(),
                aCarrinho.getUuid().getValue(),
                aCarrinho.getStatusCode().getDesc(),
                aCarrinho.getUsuario() != null ? UsuarioEntity.from(aCarrinho.getUsuario().getId().getValue()) : null,
                aCarrinho.getCriadoEm(),
                aCarrinho.getAtualizadoEm(),
                itemEntities
        );

        if (itemEntities != null) {
            itemEntities.forEach(item -> item.setCarrinho(entity));
        }

        return entity;
    }

    public static CarrinhoEntity from(final Long aCarrinhoId) {

        final var carrinho = new CarrinhoEntity();
        carrinho.setId(aCarrinhoId);

        return carrinho;
    }

    public Carrinho toDomain() {

        return Carrinho.from(
                getId(),
                uuid,
                statusDesc,
                usuario != null ? usuario.toDomainChildren() : null,
                items != null ?
                        items.stream().map(ItemCarrinhoEntity::toDomainChildren).toList() : null,
                criadoEm,
                atualizadoEm
        );
    }

    public Carrinho toDomainChildren() {

        return Carrinho.from(
                getId(),
                uuid,
                statusDesc,
                usuario != null ? usuario.toDomainSimple() : null,
                items != null ?
                        items.stream().map(ItemCarrinhoEntity::toDomainSimple).toList() : null,
                criadoEm,
                atualizadoEm
        );
    }

    public Carrinho toDomainSimple() {

        return Carrinho.from(
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
