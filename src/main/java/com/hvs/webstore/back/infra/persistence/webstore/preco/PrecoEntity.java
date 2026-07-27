package com.hvs.webstore.back.infra.persistence.webstore.preco;

import com.hvs.webstore.back.domain.entity.webstore.preco.Preco;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.webstore.produto.ProdutoEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "preco")
public class PrecoEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;
    private Long valor;
    private String tipoPagamentoDesc;
    private Integer qtdVezesParcelamento;
    private Long valorParcela;
    private Long valorTotalParcelamento;
    
    @OneToOne(mappedBy = "preco")
    private ProdutoEntity produto;

    public PrecoEntity() {

    }

    public PrecoEntity(final Long id,
                       final String uuid,
                       final String statusDesc,
                       final Long valor,
                       final String tipoPagamentoDesc,
                       final Integer qtdVezesParcelamento,
                       final Long valorParcela,
                       final Long valorTotalParcelamento,
                       final ProdutoEntity produto) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.valor = valor;
        this.tipoPagamentoDesc = tipoPagamentoDesc;
        this.qtdVezesParcelamento = qtdVezesParcelamento;
        this.valorParcela = valorParcela;
        this.valorTotalParcelamento = valorTotalParcelamento;
        this.produto = produto;
    }

    public static PrecoEntity from(Preco aPreco) {

        return new PrecoEntity(
                aPreco.getId().getValue() < 0 ? null : aPreco.getId().getValue(),
                aPreco.getUuid().getValue(),
                aPreco.getStatusCode().getDesc(),
                aPreco.getValor(),
                aPreco.getTipoPagamento().getDesc(),
                aPreco.getQtdVezesParcelamento(),
                aPreco.getValorParcela(),
                aPreco.getValorTotalParcelamento(),
                aPreco.getProduto() != null ? ProdutoEntity.from(aPreco.getProduto()) : null
        );
    }

    public static PrecoEntity from(final Long aPrecoID) {

        final var preco = new PrecoEntity();
        preco.setId(aPrecoID);

        return preco;
    }

    public Preco toDomain() {

        return Preco.from(
                getId(),
                uuid,
                statusDesc,
                valor,
                tipoPagamentoDesc,
                qtdVezesParcelamento,
                valorParcela,
                valorTotalParcelamento,
                produto != null ? produto.toDomainChildren() : null
        );
    }

    public Preco toDomainChildren() {

        return Preco.from(
                getId(),
                uuid,
                statusDesc,
                valor,
                tipoPagamentoDesc,
                qtdVezesParcelamento,
                valorParcela,
                valorTotalParcelamento,
                produto != null ? produto.toDomainSimple() : null
        );
    }

    public Preco toDomainSimple() {

        return Preco.from(
                getId(),
                uuid,
                null,
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
