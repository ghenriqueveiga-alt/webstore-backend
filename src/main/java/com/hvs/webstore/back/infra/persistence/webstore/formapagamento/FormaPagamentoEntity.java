package com.hvs.webstore.back.infra.persistence.webstore.formapagamento;

import com.hvs.webstore.back.domain.entity.webstore.formapagamento.FormaPagamento;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.webstore.boleto.BoletoEntity;
import com.hvs.webstore.back.infra.persistence.webstore.cartao.CartaoEntity;
import com.hvs.webstore.back.infra.persistence.webstore.pix.PixEntity;
import com.hvs.webstore.back.infra.persistence.webstore.usuario.UsuarioEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "forma_pagamento")
public class FormaPagamentoEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;
    private String tipoDesc;

    @ManyToOne
    @JoinColumn(name = "cartao_id")
    private CartaoEntity cartao;

    @ManyToOne
    @JoinColumn(name = "pix_id")
    private PixEntity pix;

    @ManyToOne
    @JoinColumn(name = "boleto_id")
    private BoletoEntity boleto;
    private Boolean principal;

    public FormaPagamentoEntity() {

    }

    public FormaPagamentoEntity(final Long id,
                                final String uuid,
                                final String statusDesc,
                                final UsuarioEntity usuario,
                                final String tipoDesc,
                                final CartaoEntity cartao,
                                final PixEntity pix,
                                final BoletoEntity boleto,
                                final Boolean principal) {
        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.usuario = usuario;
        this.tipoDesc = tipoDesc;
        this.cartao = cartao;
        this.pix = pix;
        this.boleto = boleto;
        this.principal = principal;
    }

    public static FormaPagamentoEntity from(final FormaPagamento aFormaPagamento) {

        return new FormaPagamentoEntity(
                aFormaPagamento.getId().getValue() < 0 ? null : aFormaPagamento.getId().getValue(),
                aFormaPagamento.getUuid().getValue(),
                aFormaPagamento.getStatusCode().getDesc(),
                aFormaPagamento.getUsuario() != null ? UsuarioEntity.from(aFormaPagamento.getUsuario().getId().getValue()) : null,
                aFormaPagamento.getTipo() != null ? aFormaPagamento.getTipo().getCode() : null,
                aFormaPagamento.getCartao() != null ? CartaoEntity.from(aFormaPagamento.getCartao().getId().getValue()) : null,
                aFormaPagamento.getPix() != null ? PixEntity.from(aFormaPagamento.getPix().getId().getValue()) : null,
                aFormaPagamento.getBoleto() != null ? BoletoEntity.from(aFormaPagamento.getBoleto().getId().getValue()) : null,
                aFormaPagamento.getPrincipal()
        );
    }

    public static FormaPagamentoEntity from(final Long aFormaPagamentoID) {

        final var formaPagamento = new FormaPagamentoEntity();
        formaPagamento.setId(aFormaPagamentoID);

        return formaPagamento;
    }

    public FormaPagamento toDomain() {

        return FormaPagamento.from(
                getId(),
                uuid,
                statusDesc,
                usuario != null ? usuario.toDomainChildren() : null,
                tipoDesc,
                cartao != null ? cartao.toDomainChildren() : null,
                pix != null ? pix.toDomainChildren() : null,
                boleto != null ? boleto.toDomainChildren() : null,
                principal
        );
    }

    public FormaPagamento toDomainChildren() {

        return FormaPagamento.from(
                getId(),
                uuid,
                statusDesc,
                usuario != null ? usuario.toDomainSimple() : null,
                tipoDesc,
                cartao != null ? cartao.toDomainSimple() : null,
                pix != null ? pix.toDomainSimple() : null,
                boleto != null ? boleto.toDomainSimple() : null,
                principal
        );
    }

    public FormaPagamento toDomainSimple() {
        return FormaPagamento.from(
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
    public Long getId() { return id; }
    public void setId(final Long id) { this.id = id; }
    public void setStatusDesc(final String statusDesc) { this.statusDesc = statusDesc; }
}
