package com.hvs.webstore.back.app.command.webstore.notafiscal;

import java.time.LocalDate;

public record UpdateNotaFiscalCommand(Long aId,
                                      String aUuid,
                                      String aStatusCode,
                                      Long aPedidoId,
                                      String aChaveAcesso,
                                      Long aNumero,
                                      Long aSerie,
                                      String aTipoAmbienteCode,
                                      String aXml,
                                      String aDanfeUrl,
                                      LocalDate aDataEmissao) {

    public static UpdateNotaFiscalCommand from(final Long aId,
                                               final UpdateNotaFiscalCommand c) {

        return new UpdateNotaFiscalCommand(
                aId,
                c.aUuid,
                c.aStatusCode,
                c.aPedidoId,
                c.aChaveAcesso,
                c.aNumero,
                c.aSerie,
                c.aTipoAmbienteCode,
                c.aXml,
                c.aDanfeUrl,
                c.aDataEmissao
        );
    }

    public static UpdateNotaFiscalCommand from(final String aUuid,
                                               final UpdateNotaFiscalCommand c) {

        return new UpdateNotaFiscalCommand(
                c.aId,
                aUuid,
                c.aStatusCode,
                c.aPedidoId,
                c.aChaveAcesso,
                c.aNumero,
                c.aSerie,
                c.aTipoAmbienteCode,
                c.aXml,
                c.aDanfeUrl,
                c.aDataEmissao
        );
    }
}
