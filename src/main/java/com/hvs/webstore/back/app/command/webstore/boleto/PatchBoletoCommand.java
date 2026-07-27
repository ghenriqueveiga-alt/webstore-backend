package com.hvs.webstore.back.app.command.webstore.boleto;

import java.time.LocalDate;

public record PatchBoletoCommand(Long aId,
                                 String aUuid,
                                 String aStatusCode,
                                 String aCodigoBarras,
                                 LocalDate aVencimento) {

    public static PatchBoletoCommand from(final Long aId,
                                          final PatchBoletoCommand aInput) {

        return new PatchBoletoCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aCodigoBarras,
                aInput.aVencimento);
    }

    public static PatchBoletoCommand from(final String aUuid,
                                          final PatchBoletoCommand aInput) {

        return new PatchBoletoCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aCodigoBarras,
                aInput.aVencimento);
    }
}
