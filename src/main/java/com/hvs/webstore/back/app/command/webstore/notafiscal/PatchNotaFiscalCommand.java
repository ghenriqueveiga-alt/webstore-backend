package com.hvs.webstore.back.app.command.webstore.notafiscal;

public record PatchNotaFiscalCommand(Long aId,
                                     String aUuid,
                                     String aStatusCode,
                                     Long aPedidoId,
                                     String aChaveAcesso,
                                     Long aNumero,
                                     Long aSerie,
                                     String aTipoAmbienteCode,
                                     String aXml,
                                     String aDanfeUrl) {

    public static PatchNotaFiscalCommand from(final String aUuid,
                                              final PatchNotaFiscalCommand aInput) {

        return new PatchNotaFiscalCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aPedidoId,
                aInput.aChaveAcesso,
                aInput.aNumero,
                aInput.aSerie,
                aInput.aTipoAmbienteCode,
                aInput.aXml,
                aInput.aDanfeUrl);
    }

    public static PatchNotaFiscalCommand from(final Long aId,
                                              final PatchNotaFiscalCommand aInput) {

        return new PatchNotaFiscalCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aPedidoId,
                aInput.aChaveAcesso,
                aInput.aNumero,
                aInput.aSerie,
                aInput.aTipoAmbienteCode,
                aInput.aXml,
                aInput.aDanfeUrl);
    }
}
