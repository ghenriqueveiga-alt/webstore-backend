package com.hvs.webstore.back.infra.persistence.webstore.pedido;

import com.hvs.webstore.back.domain.entity.webstore.pedido.Pedido;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.webstore.formapagamento.FormaPagamentoEntity;
import com.hvs.webstore.back.infra.persistence.webstore.endereco.EnderecoEntity;
import com.hvs.webstore.back.infra.persistence.webstore.usuario.UsuarioEntity;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "pedido")
public class PedidoEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "endereco_entrega_id")
    private EnderecoEntity enderecoEntrega;

    @ManyToOne
    @JoinColumn(name = "forma_pagamento_id")
    private FormaPagamentoEntity formaPagamento;
    private Instant dataCriacao;
    private Instant dataPagamento;
    private Instant dataEnvio;
    private Long total;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedidoEntity> items;

    public PedidoEntity() {

    }

    public PedidoEntity(final Long id,
                        final String uuid,
                        final String statusDesc,
                        final UsuarioEntity usuario,
                        final EnderecoEntity enderecoEntrega,
                        final FormaPagamentoEntity formaPagamento,
                        final Instant dataCriacao,
                        final Instant dataPagamento,
                        final Instant dataEnvio,
                        final Long total,
                        List<ItemPedidoEntity> items) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.usuario = usuario;
        this.enderecoEntrega = enderecoEntrega;
        this.formaPagamento = formaPagamento;
        this.dataCriacao = dataCriacao;
        this.dataPagamento = dataPagamento;
        this.dataEnvio = dataEnvio;
        this.total = total;
        this.items = items;
    }

    public static PedidoEntity from(Pedido aPedido) {

        return new PedidoEntity(
                aPedido.getId().getValue() < 0 ? null : aPedido.getId().getValue(),
                aPedido.getUuid().getValue(),
                aPedido.getStatusCode().getDesc(),
                aPedido.getUsuario() != null ? UsuarioEntity.from(aPedido.getUsuario().getId().getValue()) : null,
                aPedido.getEnderecoEntrega() != null ? EnderecoEntity.from(aPedido.getEnderecoEntrega().getId().getValue()) : null,
                aPedido.getFormaPagamento() != null ? FormaPagamentoEntity.from(aPedido.getFormaPagamento().getId().getValue()) : null,
                aPedido.getDataCriacao(),
                aPedido.getDataPagamento(),
                aPedido.getDataEnvio(),
                aPedido.getTotal(),
                aPedido.getItems() != null ?
                        aPedido.getItems().stream().map(ItemPedidoEntity::from).toList() : null
        );
    }

    public static PedidoEntity from(final Long aPedidoId) {

        final var pedido = new PedidoEntity();
        pedido.setId(aPedidoId);

        return pedido;
    }

    public Pedido toDomain() {

        return Pedido.from(
                getId(),
                uuid,
                statusDesc,
                usuario != null ? usuario.toDomainChildren() : null,
                enderecoEntrega != null ? enderecoEntrega.toDomainChildren() : null,
                formaPagamento != null ? formaPagamento.toDomainChildren() : null,
                dataCriacao,
                dataPagamento,
                dataEnvio,
                total,
                items != null ?
                        items.stream().map(ItemPedidoEntity::toDomainChildren).toList() : null
        );
    }

    public Pedido toDomainChildren() {

        return Pedido.from(
                getId(),
                uuid,
                statusDesc,
                usuario != null ? usuario.toDomainSimple() : null,
                enderecoEntrega != null ? enderecoEntrega.toDomainSimple() : null,
                formaPagamento != null ? formaPagamento.toDomainSimple() : null,
                dataCriacao,
                dataPagamento,
                dataEnvio,
                total,
                items != null ?
                        items.stream().map(ItemPedidoEntity::toDomainSimple).toList() : null
        );
    }

    public Pedido toDomainSimple() {

        return Pedido.from(
                getId(),
                uuid,
                null,
                null,
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
