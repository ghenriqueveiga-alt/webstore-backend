package com.hvs.webstore.back.app.command.webstore.boleto;

import java.time.LocalDate;

public record UpdateBoletoCommand(Long aId,
                                  String aUuid,
                                  String aStatusCode,
                                  String aCodigoBarras,
                                  LocalDate aVencimento) {

    public static UpdateBoletoCommand from(final Long aId,
                                           final UpdateBoletoCommand aInput) {

        return new UpdateBoletoCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aCodigoBarras,
                aInput.aVencimento
        );
    }

    public static UpdateBoletoCommand from(final String aUuid,
                                           final UpdateBoletoCommand aInput) {

        return new UpdateBoletoCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aCodigoBarras,
                aInput.aVencimento
        );
    }
}
