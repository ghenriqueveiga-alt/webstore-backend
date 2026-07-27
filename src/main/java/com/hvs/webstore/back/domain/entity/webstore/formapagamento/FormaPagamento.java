package com.hvs.webstore.back.domain.entity.webstore.formapagamento;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.webstore.boleto.Boleto;
import com.hvs.webstore.back.domain.entity.webstore.cartao.Cartao;
import com.hvs.webstore.back.domain.entity.webstore.pix.Pix;
import com.hvs.webstore.back.domain.entity.webstore.usuario.Usuario;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.util.Objects;

public class FormaPagamento extends Entity<FormaPagamentoId> {

    private final FormaPagamentoUuid uuid;
    private final FormaPagamentoStatus statusCode;
    private final Usuario usuario;
    private final TipoPagamento tipo;
    private final Cartao cartao;
    private final Pix pix;
    private final Boleto boleto;
    private final Boolean principal;

    private FormaPagamento(final FormaPagamentoId id,
                           final FormaPagamentoUuid uuid,
                           final FormaPagamentoStatus statusCode,
                           final Usuario usuario,
                           final TipoPagamento tipo,
                           final Cartao cartao,
                           final Pix pix,
                           final Boleto boleto,
                           final Boolean principal) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.usuario = usuario;
        this.tipo = tipo;
        this.cartao = cartao;
        this.pix = pix;
        this.boleto = boleto;
        this.principal = principal;
    }

    public static FormaPagamento create(final Long aUsuarioId,
                                        final String aTipo,
                                        final Long aCartaoId,
                                        final Long aPixId,
                                        final Long aBoletoId,
                                        final Boolean aPrincipal) {

        return new FormaPagamento(
                FormaPagamentoId.from(-1L),
                FormaPagamentoUuid.unique(),
                FormaPagamentoStatus.ACTIVE,
                aUsuarioId != null ? Usuario.from(aUsuarioId) : null,
                aTipo != null ? TipoPagamento.findByDesc(aTipo) : null,
                aCartaoId != null ? Cartao.from(aCartaoId) : null,
                aPixId != null ? Pix.from(aPixId) : null,
                aBoletoId != null ? Boleto.from(aBoletoId) : null,
                aPrincipal);
    }

    public static FormaPagamento update(final Long aId,
                                        final String aUuid,
                                        final String aStatusCode,
                                        final Long aUsuarioId,
                                        final String aTipo,
                                        final Long aCartaoId,
                                        final Long aPixId,
                                        final Long aBoletoId,
                                        final Boolean aPrincipal) {

        return new FormaPagamento(
                aId != null ? FormaPagamentoId.from(aId) : null,
                aUuid != null ? FormaPagamentoUuid.from(aUuid) : null,
                aStatusCode != null ? FormaPagamentoStatus.findByCode(aStatusCode) : null,
                aUsuarioId != null ? Usuario.from(aUsuarioId) : null,
                aTipo != null ? TipoPagamento.findByCode(aTipo) : null,
                aCartaoId != null ? Cartao.from(aCartaoId) : null,
                aPixId != null ? Pix.from(aPixId) : null,
                aBoletoId != null ? Boleto.from(aBoletoId) : null,
                aPrincipal);
    }

    public static FormaPagamento patch(final String aStatusCode,
                                       final String aTipo,
                                       final Long aCartaoId,
                                       final Long aPixId,
                                       final Long aBoletoId,
                                       final Boolean aPrincipal,
                                       final FormaPagamento aExisting) {

        return new FormaPagamento(
                aExisting.getId(),
                aExisting.getUuid(),
                aStatusCode != null ? FormaPagamentoStatus.findByCode(aStatusCode) : aExisting.getStatusCode(),
                aExisting.getUsuario(),
                aTipo != null ? TipoPagamento.findByCode(aTipo) : aExisting.getTipo(),
                aCartaoId != null ? Cartao.from(aCartaoId) : aExisting.getCartao(),
                aPixId != null ? Pix.from(aPixId) : aExisting.getPix(),
                aBoletoId != null ? Boleto.from(aBoletoId) : aExisting.getBoleto(),
                aPrincipal != null ? aPrincipal : aExisting.getPrincipal());
    }

    public static FormaPagamento from(final Long aId,
                                      final String aUuid,
                                      final String aStatusDesc,
                                      final Usuario aUsuario,
                                      final String aTipoDesc,
                                      final Cartao aCartao,
                                      final Pix aPix,
                                      final Boleto aBoleto,
                                      final Boolean aPrincipal) {

        return new FormaPagamento(
                aId != null ? FormaPagamentoId.from(aId) : null,
                aUuid != null ? FormaPagamentoUuid.from(aUuid) : null,
                aStatusDesc != null ? FormaPagamentoStatus.findByDesc(aStatusDesc) : null,
                aUsuario,
                aTipoDesc != null ? TipoPagamento.findByDesc(aTipoDesc) : null,
                aCartao,
                aPix,
                aBoleto,
                aPrincipal);
    }

    public static FormaPagamento from(final Long aId) {

        return new FormaPagamento(
                aId != null ? FormaPagamentoId.from(aId) : null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public static FormaPagamento from(final String aUuid) {

        return new FormaPagamento(
                null,
                aUuid != null ? FormaPagamentoUuid.from(aUuid) : null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new FormaPagamentoValidator(aHandler, this).validate();
    }

    public FormaPagamentoUuid getUuid() {
        return uuid;
    }
    public FormaPagamentoStatus getStatusCode() {
        return statusCode;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public TipoPagamento getTipo() {
        return tipo;
    }
    public Cartao getCartao() {
        return cartao;
    }
    public Pix getPix() {
        return pix;
    }
    public Boleto getBoleto() {
        return boleto;
    }
    public Boolean getPrincipal() {
        return principal;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        FormaPagamento that = (FormaPagamento) o;

        return Objects.equals(uuid, that.uuid) &&
                statusCode == that.statusCode &&
                Objects.equals(usuario, that.usuario) &&
                tipo == that.tipo &&
                Objects.equals(cartao, that.cartao) &&
                Objects.equals(pix, that.pix) &&
                Objects.equals(boleto, that.boleto) &&
                Objects.equals(principal, that.principal);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                usuario,
                tipo,
                cartao,
                pix,
                boleto,
                principal);
    }
}
