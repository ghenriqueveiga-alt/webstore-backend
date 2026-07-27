package com.hvs.webstore.back.infra.persistence.webstore.estoque;

import com.hvs.webstore.back.domain.entity.webstore.estoque.Estoque;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.webstore.produto.ProdutoEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "estoque")
public class EstoqueEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoEntity produto;
    private Integer quantidade;
    private Integer reservado;
    private Integer quantidadeMinima;

    public EstoqueEntity() {

    }

    public EstoqueEntity(final Long id,
                         final String uuid,
                         final String statusDesc,
                         final ProdutoEntity produto,
                         final Integer quantidade,
                         final Integer reservado,
                         final Integer quantidadeMinima) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.produto = produto;
        this.quantidade = quantidade;
        this.reservado = reservado;
        this.quantidadeMinima = quantidadeMinima;
    }

    public static EstoqueEntity from(final Estoque aEstoque) {

        return new EstoqueEntity(
                aEstoque.getId().getValue() < 0 ? null : aEstoque.getId().getValue(),
                aEstoque.getUuid().getValue(),
                aEstoque.getStatusCode().getDesc(),
                aEstoque.getProduto() != null ? ProdutoEntity.from(aEstoque.getProduto().getId().getValue()) : null,
                aEstoque.getQuantidade(),
                aEstoque.getReservado(),
                aEstoque.getQuantidadeMinima()
        );
    }

    public static EstoqueEntity from(final Long aEstoqueId) {

        final var estoque = new EstoqueEntity();
        estoque.setId(aEstoqueId);

        return estoque;
    }

    public Estoque toDomain() {

        return Estoque.from(
                getId(),
                uuid,
                statusDesc,
                produto != null ? produto.toDomainChildren() : null,
                quantidade,
                reservado,
                quantidadeMinima
        );
    }

    public Estoque toDomainChildren() {

        return Estoque.from(
                getId(),
                uuid,
                statusDesc,
                produto != null ? produto.toDomainSimple() : null,
                quantidade,
                reservado,
                quantidadeMinima
        );
    }

    public Estoque toDomainSimple() {

        return Estoque.from(
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
