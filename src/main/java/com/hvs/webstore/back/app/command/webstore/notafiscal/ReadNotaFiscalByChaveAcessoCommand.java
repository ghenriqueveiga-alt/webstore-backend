package com.hvs.webstore.back.app.command.webstore.notafiscal;

public record ReadNotaFiscalByChaveAcessoCommand(String aChaveAcesso) {

    public static ReadNotaFiscalByChaveAcessoCommand from(final String aChaveAcesso) {

        return new ReadNotaFiscalByChaveAcessoCommand(aChaveAcesso);
    }
}
