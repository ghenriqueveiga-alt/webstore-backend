package com.hvs.webstore.back.app.command.webstore.notafiscal;

public record DeleteNotaFiscalCommand(Long aId,
                                      String aUuid) {

    public static DeleteNotaFiscalCommand from(final Long aId) {

        return new DeleteNotaFiscalCommand(
                aId,
                null);
    }

    public static DeleteNotaFiscalCommand from(final String aUuid) {

        return new DeleteNotaFiscalCommand(
                null,
                aUuid);
    }
}
