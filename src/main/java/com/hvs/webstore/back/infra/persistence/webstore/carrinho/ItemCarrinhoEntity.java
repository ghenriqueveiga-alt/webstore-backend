package com.hvs.webstore.back.infra.persistence.webstore.carrinho;

import com.hvs.webstore.back.domain.entity.webstore.carrinho.ItemCarrinho;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.webstore.produto.ProdutoEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "item_carrinho")
public class ItemCarrinhoEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;

    @ManyToOne
    @JoinColumn(name = "carrinho_id")
    private CarrinhoEntity carrinho;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoEntity produto;
    private Integer quantidade;

    public ItemCarrinhoEntity() {

    }

    public ItemCarrinhoEntity(final Long id,
                               final String uuid,
                               final String statusDesc,
                               final CarrinhoEntity carrinho,
                               final ProdutoEntity produto,
                               final Integer quantidade) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.carrinho = carrinho;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public static ItemCarrinhoEntity from(ItemCarrinho aItem) {

        return new ItemCarrinhoEntity(
                aItem.getId().getValue() < 0 ? null : aItem.getId().getValue(),
                aItem.getUuid().getValue(),
                aItem.getStatusCode().getDesc(),
                null,
                aItem.getProduto() != null ? ProdutoEntity.from(aItem.getProduto().getId().getValue()) : null,
                aItem.getQuantidade()
        );
    }

    public static ItemCarrinhoEntity from(final Long aItemId) {

        final var item = new ItemCarrinhoEntity();
        item.setId(aItemId);

        return item;
    }

    public ItemCarrinho toDomain() {

        return ItemCarrinho.from(
                getId(),
                uuid,
                statusDesc,
                produto != null ? produto.toDomainChildren() : null,
                quantidade
        );
    }

    public ItemCarrinho toDomainChildren() {

        return ItemCarrinho.from(
                getId(),
                uuid,
                statusDesc,
                produto != null ? produto.toDomain() : null,
                quantidade
        );
    }

    public ItemCarrinho toDomainSimple() {

        return ItemCarrinho.from(
                getId(),
                uuid,
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
    public void setCarrinho(final CarrinhoEntity aCarrinho) {
        this.carrinho = aCarrinho;
    }
}
