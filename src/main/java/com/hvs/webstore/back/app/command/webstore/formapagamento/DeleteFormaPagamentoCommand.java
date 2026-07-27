package com.hvs.webstore.back.app.command.webstore.formapagamento;

public record DeleteFormaPagamentoCommand(Long aId,
                                          String aUuid) {

    public static DeleteFormaPagamentoCommand from(final Long aId) {

        return new DeleteFormaPagamentoCommand(
                aId,
                null);
    }

    public static DeleteFormaPagamentoCommand from(final String aUuid) {

        return new DeleteFormaPagamentoCommand(
                null,
                aUuid);
    }
}
