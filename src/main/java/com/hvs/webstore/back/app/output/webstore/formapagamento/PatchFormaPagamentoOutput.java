package com.hvs.webstore.back.app.output.webstore.formapagamento;

import com.hvs.webstore.back.domain.entity.webstore.formapagamento.FormaPagamento;

public record PatchFormaPagamentoOutput(Long aId,
                                        String aUuid,
                                        String aMessage) {

    public static PatchFormaPagamentoOutput from(FormaPagamento aFormaPagamento) {

        return new PatchFormaPagamentoOutput(
                aFormaPagamento.getId().getValue(),
                aFormaPagamento.getUuid().getValue(),
                "The FormaPagamento with id: " + aFormaPagamento.getUuid().getValue() + " has been successfully patched.");
    }
}
