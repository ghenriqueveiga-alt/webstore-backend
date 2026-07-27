package com.hvs.webstore.back.app.command.webstore.notafiscal;

public record ReadNotaFiscalCommand(Long aId,
                                    String aUuid) {

    public static ReadNotaFiscalCommand from(final Long aId) {

        return new ReadNotaFiscalCommand(
                aId,
                null
        );
    }

    public static ReadNotaFiscalCommand from(final String aUuid) {

        return new ReadNotaFiscalCommand(
                null,
                aUuid
        );
    }
}
