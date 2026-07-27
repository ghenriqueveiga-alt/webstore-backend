package com.hvs.webstore.back.app.command.webstore.notafiscal;

public record CreateNotaFiscalCommand(Long aPedidoId,
                                      String aChaveAcesso,
                                      Long aNumero,
                                      Long aSerie,
                                      String aTipoAmbienteCode,
                                      String aXml,
                                      String aDanfeUrl) {

    public static CreateNotaFiscalCommand from(final Long aPedidoId,
                                               final String aChaveAcesso,
                                               final Long aNumero,
                                               final Long aSerie,
                                               final String aTipoAmbienteCode,
                                               final String aXml,
                                               final String aDanfeUrl) {

        return new CreateNotaFiscalCommand(
                aPedidoId,
                aChaveAcesso,
                aNumero,
                aSerie,
                aTipoAmbienteCode,
                aXml,
                aDanfeUrl);
    }
}
