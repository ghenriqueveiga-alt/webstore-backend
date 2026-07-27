package com.hvs.webstore.back.infra.persistence.webstore.variacaoproduto;

import com.hvs.webstore.back.domain.entity.webstore.variacaoproduto.VariacaoProduto;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.webstore.produto.ProdutoEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "variacao_produto")
public class VariacaoProdutoEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoEntity produto;
    private String nome;
    private String valor;
    private String sku;
    private Integer estoque;

    public VariacaoProdutoEntity() {

    }

    public VariacaoProdutoEntity(final Long id,
                                  final String uuid,
                                  final String statusDesc,
                                  final ProdutoEntity produto,
                                  final String nome,
                                  final String valor,
                                  final String sku,
                                  final Integer estoque) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.produto = produto;
        this.nome = nome;
        this.valor = valor;
        this.sku = sku;
        this.estoque = estoque;
    }

    public static VariacaoProdutoEntity from(VariacaoProduto aVariacaoProduto) {

        return new VariacaoProdutoEntity(
                aVariacaoProduto.getId().getValue() < 0 ? null : aVariacaoProduto.getId().getValue(),
                aVariacaoProduto.getUuid().getValue(),
                aVariacaoProduto.getStatusCode().getDesc(),
                aVariacaoProduto.getProduto() != null ? ProdutoEntity.from(aVariacaoProduto.getProduto().getId().getValue()) : null,
                aVariacaoProduto.getNome(),
                aVariacaoProduto.getValor(),
                aVariacaoProduto.getSku(),
                aVariacaoProduto.getEstoque()
        );
    }

    public static VariacaoProdutoEntity from(final Long aVariacaoProdutoId) {

        final var variacaoProduto = new VariacaoProdutoEntity();
        variacaoProduto.setId(aVariacaoProdutoId);

        return variacaoProduto;
    }

    public VariacaoProduto toDomain() {

        return VariacaoProduto.from(
                getId(),
                uuid,
                statusDesc,
                produto != null ? produto.toDomainChildren(): null,
                nome,
                valor,
                sku,
                estoque
        );
    }

    public VariacaoProduto toDomainChildren() {

        return VariacaoProduto.from(
                getId(),
                uuid,
                statusDesc,
                produto != null ? produto.toDomainSimple() : null,
                nome,
                valor,
                sku,
                estoque
        );
    }

    public VariacaoProduto toDomainSimple() {

        return VariacaoProduto.from(
                getId(),
                uuid,
                null,
                null,
                nome,
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
