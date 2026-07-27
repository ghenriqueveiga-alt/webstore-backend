package com.hvs.webstore.back.app.command.webstore.preco;

public record PatchPrecoCommand(Long aId,
                                String aUuid,
                                String aStatusCode,
                                Long aValor,
                                String aTipoPagamento,
                                Integer aQtdVezesParcelamento,
                                Long aValorParcela,
                                Long aValorTotalParcelamento) {

    public static PatchPrecoCommand from(final Long aId,
                                         final PatchPrecoCommand aInput) {

        return new PatchPrecoCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aValor,
                aInput.aTipoPagamento,
                aInput.aQtdVezesParcelamento,
                aInput.aValorParcela,
                aInput.aValorTotalParcelamento);
    }

    public static PatchPrecoCommand from(final String aUuid,
                                         final PatchPrecoCommand aInput) {

        return new PatchPrecoCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aValor,
                aInput.aTipoPagamento,
                aInput.aQtdVezesParcelamento,
                aInput.aValorParcela,
                aInput.aValorTotalParcelamento);
    }
}