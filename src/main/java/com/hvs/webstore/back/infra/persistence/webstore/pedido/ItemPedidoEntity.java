package com.hvs.webstore.back.infra.persistence.webstore.pedido;

import com.hvs.webstore.back.domain.entity.webstore.pedido.ItemPedido;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.webstore.preco.PrecoEntity;
import com.hvs.webstore.back.infra.persistence.webstore.produto.ProdutoEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "item_pedido")
public class ItemPedidoEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private PedidoEntity pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoEntity produto;

    @ManyToOne
    @JoinColumn(name = "preco_id")
    private PrecoEntity preco;
    private Integer quantidade;
    private Long subtotal;

    public ItemPedidoEntity() {

    }

    public ItemPedidoEntity(final Long id,
                           final String uuid,
                           final String statusDesc,
                           final PedidoEntity pedido,
                           final ProdutoEntity produto,
                           final PrecoEntity preco,
                           final Integer quantidade,
                           final Long subtotal) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.pedido = pedido;
        this.produto = produto;
        this.preco = preco;
        this.quantidade = quantidade;
        this.subtotal = subtotal;
    }

    public static ItemPedidoEntity from(ItemPedido aItem) {

        return new ItemPedidoEntity(
                aItem.getId().getValue() < 0 ? null : aItem.getId().getValue(),
                aItem.getUuid().getValue(),
                aItem.getStatusCode().getDesc(),
                aItem.getPedido() != null ? PedidoEntity.from(aItem.getPedido().getId().getValue()) : null,
                aItem.getProduto() != null ? ProdutoEntity.from(aItem.getProduto().getId().getValue()) : null,
                aItem.getPreco() != null ? PrecoEntity.from(aItem.getPreco().getId().getValue()) : null,
                aItem.getQuantidade(),
                aItem.getSubtotal()
        );
    }

    public ItemPedido toDomain() {

        return ItemPedido.from(
                getId(),
                uuid,
                statusDesc,
                pedido != null ? pedido.toDomainChildren() : null,
                produto != null ? produto.toDomainChildren() : null,
                quantidade,
                preco != null ? preco.toDomainChildren() : null,
                subtotal
        );
    }

    public ItemPedido toDomainChildren() {

        return ItemPedido.from(
                getId(),
                uuid,
                statusDesc,
                pedido != null ? pedido.toDomainSimple() : null,
                produto != null ? produto.toDomainSimple() : null,
                quantidade,
                preco != null ? preco.toDomainSimple() : null,
                subtotal
        );
    }

    public ItemPedido toDomainSimple() {

        return ItemPedido.from(
                getId(),
                uuid,
                null,
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
