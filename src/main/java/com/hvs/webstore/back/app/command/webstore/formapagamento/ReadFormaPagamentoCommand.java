package com.hvs.webstore.back.app.command.webstore.formapagamento;

public record ReadFormaPagamentoCommand(Long aId,
                                        String aUuid) {

    public static ReadFormaPagamentoCommand from(final Long aId) {

        return new ReadFormaPagamentoCommand(
                aId,
                null
        );
    }

    public static ReadFormaPagamentoCommand from(final String aUuid) {

        return new ReadFormaPagamentoCommand(
                null,
                aUuid
        );
    }
}
