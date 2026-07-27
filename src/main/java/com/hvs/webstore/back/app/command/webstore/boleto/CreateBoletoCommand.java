package com.hvs.webstore.back.app.command.webstore.boleto;

import java.time.LocalDate;

public record CreateBoletoCommand(String aCodigoBarras,
                                  LocalDate aVencimento) {

    public static CreateBoletoCommand from(final String aCodigoBarras,
                                           final LocalDate aVencimento) {

        return new CreateBoletoCommand(
                aCodigoBarras,
                aVencimento);
    }
}
