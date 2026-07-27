package com.hvs.webstore.back.app.command.webstore.formapagamento;

import java.time.LocalDate;

public record UpdateFormaPagamentoCommand(Long aId,
                                          String aUuid,
                                          String aStatusCode,
                                          Long aUsuarioId,
                                          String aTipo,
                                          Long aCartaoId,
                                          String aChavePix,
                                          String aTipoChavePix,
                                          String aCodigoBarras,
                                          LocalDate aVencimento,
                                          Boolean aPrincipal) {

    public static UpdateFormaPagamentoCommand from(final Long aId,
                                                   final UpdateFormaPagamentoCommand aInput) {

        return new UpdateFormaPagamentoCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aUsuarioId,
                aInput.aTipo,
                aInput.aCartaoId,
                aInput.aChavePix,
                aInput.aTipoChavePix,
                aInput.aCodigoBarras,
                aInput.aVencimento,
                aInput.aPrincipal
        );
    }

    public static UpdateFormaPagamentoCommand from(final String aUuid,
                                                   final UpdateFormaPagamentoCommand aInput) {

        return new UpdateFormaPagamentoCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aUsuarioId,
                aInput.aTipo,
                aInput.aCartaoId,
                aInput.aChavePix,
                aInput.aTipoChavePix,
                aInput.aCodigoBarras,
                aInput.aVencimento,
                aInput.aPrincipal
        );
    }
}
