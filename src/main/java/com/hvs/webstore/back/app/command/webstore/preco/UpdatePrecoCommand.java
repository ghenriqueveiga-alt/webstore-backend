package com.hvs.webstore.back.app.command.webstore.preco;

public record UpdatePrecoCommand(Long aId,
                                 String aUuid,
                                 String aStatusCode,
                                 Long aValor,
                                 String aTipoPagamento,
                                 Integer aQtdVezesParcelamento,
                                 Long aValorParcela,
                                 Long aValorTotalParcelamento) {

    public static UpdatePrecoCommand from(final Long aId,
                                          final UpdatePrecoCommand aInput) {

        return new UpdatePrecoCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aValor,
                aInput.aTipoPagamento,
                aInput.aQtdVezesParcelamento,
                aInput.aValorParcela,
                aInput.aValorTotalParcelamento
        );
    }

    public static UpdatePrecoCommand from(final String aUuid,
                                          final UpdatePrecoCommand aInput) {

        return new UpdatePrecoCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aValor,
                aInput.aTipoPagamento,
                aInput.aQtdVezesParcelamento,
                aInput.aValorParcela,
                aInput.aValorTotalParcelamento
        );
    }
}
