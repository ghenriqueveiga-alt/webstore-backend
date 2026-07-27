package com.hvs.webstore.back.app.command.webstore.metaloja;

public record PatchMetaLojaCommand(Long aId,
                                   String aUuid,
                                   String aStatusCode,
                                   String aChave,
                                   String aValor,
                                   String aDescricao,
                                   String aTipoCode) {

    public static PatchMetaLojaCommand from(final Long aId,
                                            final PatchMetaLojaCommand aIn) {

        return new PatchMetaLojaCommand(
                aId,
                null,
                aIn.aStatusCode,
                aIn.aChave,
                aIn.aValor,
                aIn.aDescricao,
                aIn.aTipoCode);
    }

    public static PatchMetaLojaCommand from(final String aUuid,
                                            final PatchMetaLojaCommand aIn) {

        return new PatchMetaLojaCommand(
                null,
                aUuid,
                aIn.aStatusCode,
                aIn.aChave,
                aIn.aValor,
                aIn.aDescricao,
                aIn.aTipoCode);
    }
}
