package com.hvs.webstore.back.app.output.webstore.formapagamento;

import com.hvs.webstore.back.domain.entity.webstore.formapagamento.FormaPagamento;

public record UpdateFormaPagamentoOutput(Long aId,
                                         String aUuid,
                                         String aMessage) {

    public static UpdateFormaPagamentoOutput from(FormaPagamento aFormaPagamento) {

        return new UpdateFormaPagamentoOutput(
                aFormaPagamento.getId().getValue(),
                aFormaPagamento.getUuid().getValue(),
                "The FormaPagamento with id: " + aFormaPagamento.getUuid().getValue() + " has been successfully updated.");
    }
}
