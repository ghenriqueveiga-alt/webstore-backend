package com.hvs.webstore.back.app.command.webstore.preco;

public record CreatePrecoCommand(Long valor,
                                 String tipoPagamento,
                                 Integer qtdVezesParcelamento,
                                 Long valorParcela,
                                 Long valorTotalParcelamento) {

    public static CreatePrecoCommand from(final Long valor,
                                          final String tipoPagamento,
                                          final Integer qtdVezesParcelamento,
                                          final Long valorParcela,
                                          final Long valorTotalParcelamento) {

        return new CreatePrecoCommand(
                valor,
                tipoPagamento,
                qtdVezesParcelamento,
                valorParcela,
                valorTotalParcelamento);
    }
}
