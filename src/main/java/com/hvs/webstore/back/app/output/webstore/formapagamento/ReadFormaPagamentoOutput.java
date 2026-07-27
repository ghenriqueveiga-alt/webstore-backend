package com.hvs.webstore.back.app.output.webstore.formapagamento;

import com.hvs.webstore.back.domain.entity.webstore.formapagamento.FormaPagamento;

public record ReadFormaPagamentoOutput(Long aId,
                                       String aUuid,
                                       String aStatusDesc,
                                       Long aUsuarioId,
                                       String aTipo,
                                       Long aCartaoId,
                                       String aNomeTitular,
                                       String aNumero,
                                       String aBandeira,
                                       Integer aMesVencimento,
                                       Integer aAnoVencimento,
                                       String aCvv,
                                       String aChavePix,
                                       String aTipoChavePix,
                                       String aCodigoBarras,
                                       String aVencimento,
                                       Boolean aPrincipal) {

    public static ReadFormaPagamentoOutput from(FormaPagamento aFormaPagamento) {

        return new ReadFormaPagamentoOutput(
                aFormaPagamento.getId().getValue(),
                aFormaPagamento.getUuid().getValue(),
                aFormaPagamento.getStatusCode().getDesc(),
                aFormaPagamento.getUsuario() != null ? aFormaPagamento.getUsuario().getId().getValue() : null,
                aFormaPagamento.getTipo() != null ? aFormaPagamento.getTipo().getCode() : null,
                aFormaPagamento.getCartao() != null ? aFormaPagamento.getCartao().getId().getValue() : null,
                aFormaPagamento.getCartao() != null ? aFormaPagamento.getCartao().getNomeTitular() : null,
                aFormaPagamento.getCartao() != null ? aFormaPagamento.getCartao().getNumero() : null,
                aFormaPagamento.getCartao() != null ? aFormaPagamento.getCartao().getBandeira() : null,
                aFormaPagamento.getCartao() != null ? aFormaPagamento.getCartao().getMesVencimento() : null,
                aFormaPagamento.getCartao() != null ? aFormaPagamento.getCartao().getAnoVencimento() : null,
                aFormaPagamento.getCartao() != null ? aFormaPagamento.getCartao().getCvv() : null,
                aFormaPagamento.getPix() != null ? aFormaPagamento.getPix().getChavePix() : null,
                aFormaPagamento.getPix() != null ? aFormaPagamento.getPix().getTipoChavePix().getDesc() : null,
                aFormaPagamento.getBoleto() != null ? aFormaPagamento.getBoleto().getCodigoBarras() : null,
                aFormaPagamento.getBoleto() != null ? aFormaPagamento.getBoleto().getVencimento() : null,
                aFormaPagamento.getPrincipal());
    }

    public static ReadFormaPagamentoOutput fromSimple(FormaPagamento aFormaPagamento) {

        return new ReadFormaPagamentoOutput(
                aFormaPagamento.getId().getValue(),
                aFormaPagamento.getUuid().getValue(),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }
}
