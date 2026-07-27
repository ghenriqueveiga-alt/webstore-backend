package com.hvs.webstore.back.app.command.webstore.pix;

public record PatchPixCommand(Long aId,
                              String aUuid,
                              String aStatusCode,
                              String aChavePix,
                              String aTipoChavePix) {

    public static PatchPixCommand from(final Long aId,
                                       final PatchPixCommand aInput) {

        return new PatchPixCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aChavePix,
                aInput.aTipoChavePix);
    }

    public static PatchPixCommand from(final String aUuid,
                                       final PatchPixCommand aInput) {

        return new PatchPixCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aChavePix,
                aInput.aTipoChavePix);
    }
}
