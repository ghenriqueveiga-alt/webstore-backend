package com.hvs.webstore.back.app.command.webstore.formapagamento;

import java.time.LocalDate;

public record CreateFormaPagamentoCommand(Long aUsuarioId,
                                          String aTipo,
                                          Long aCartaoId,
                                          String aChavePix,
                                          String aTipoChavePix,
                                          String aCodigoBarras,
                                          LocalDate aVencimento,
                                          Boolean aPrincipal) {

    public static CreateFormaPagamentoCommand from(final Long aUsuarioId,
                                                   final String aTipo,
                                                   final Long aCartaoId,
                                                   final String aChavePix,
                                                   final String aTipoChavePix,
                                                   final String aCodigoBarras,
                                                   final LocalDate aVencimento,
                                                   final Boolean aPrincipal) {

        return new CreateFormaPagamentoCommand(
                aUsuarioId,
                aTipo,
                aCartaoId,
                aChavePix,
                aTipoChavePix,
                aCodigoBarras,
                aVencimento,
                aPrincipal);
    }
}
