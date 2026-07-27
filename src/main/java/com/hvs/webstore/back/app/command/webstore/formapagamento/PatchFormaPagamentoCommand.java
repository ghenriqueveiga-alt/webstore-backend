package com.hvs.webstore.back.app.command.webstore.formapagamento;

import java.time.LocalDate;

public record PatchFormaPagamentoCommand(Long aId,
                                         String aUuid,
                                         String aStatusCode,
                                         String aTipo,
                                         Long aCartaoId,
                                         String aChavePix,
                                         String aTipoChavePix,
                                         String aCodigoBarras,
                                         LocalDate aVencimento,
                                         Boolean aPrincipal) {

    public static PatchFormaPagamentoCommand from(final Long aId,
                                                  final PatchFormaPagamentoCommand aInput) {

        return new PatchFormaPagamentoCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aTipo,
                aInput.aCartaoId,
                aInput.aChavePix,
                aInput.aTipoChavePix,
                aInput.aCodigoBarras,
                aInput.aVencimento,
                aInput.aPrincipal
        );
    }

    public static PatchFormaPagamentoCommand from(final String aUuid,
                                                  final PatchFormaPagamentoCommand aInput) {

        return new PatchFormaPagamentoCommand(
                null,
                aUuid,
                aInput.aStatusCode,
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
