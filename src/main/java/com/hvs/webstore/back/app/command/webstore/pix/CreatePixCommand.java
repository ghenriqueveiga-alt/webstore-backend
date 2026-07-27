package com.hvs.webstore.back.app.command.webstore.pix;

public record CreatePixCommand(String aChavePix,
                               String aTipoChavePix) {

    public static CreatePixCommand from(final String aChavePix,
                                        final String aTipoChavePix) {

        return new CreatePixCommand(
                aChavePix,
                aTipoChavePix);
    }
}
