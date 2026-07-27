package com.hvs.webstore.back.app.command.webstore.metaloja;

public record UpdateMetaLojaCommand(Long aId,
                                    String aUuid,
                                    String aStatusCode,
                                    String aChave,
                                    String aValor,
                                    String aDescricao,
                                    String aTipoCode) {

    public static UpdateMetaLojaCommand from(final Long aId,
                                             final UpdateMetaLojaCommand aIn) {

        return new UpdateMetaLojaCommand(
                aId,
                null,
                aIn.aStatusCode,
                aIn.aChave,
                aIn.aValor,
                aIn.aDescricao,
                aIn.aTipoCode
        );
    }

    public static UpdateMetaLojaCommand from(final String aUuid,
                                             final UpdateMetaLojaCommand aIn) {

        return new UpdateMetaLojaCommand(
                null,
                aUuid,
                aIn.aStatusCode,
                aIn.aChave,
                aIn.aValor,
                aIn.aDescricao,
                aIn.aTipoCode
        );
    }
}
