package com.hvs.webstore.back.app.command.webstore.pix;

public record UpdatePixCommand(Long aId,
                               String aUuid,
                               String aStatusCode,
                               String aChavePix,
                               String aTipoChavePix) {

    public static UpdatePixCommand from(final Long aId,
                                        final UpdatePixCommand aInput) {

        return new UpdatePixCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aChavePix,
                aInput.aTipoChavePix
        );
    }

    public static UpdatePixCommand from(final String aUuid,
                                        final UpdatePixCommand aInput) {

        return new UpdatePixCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aChavePix,
                aInput.aTipoChavePix
        );
    }
}
