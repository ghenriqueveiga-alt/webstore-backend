package com.hvs.webstore.back.app.command.webstore.metaloja;

public record ReadMetaLojaCommand(Long aId,
                                  String aUuid) {

    public static ReadMetaLojaCommand from(final Long aId) {

        return new ReadMetaLojaCommand(
                aId,
                null
        );
    }

    public static ReadMetaLojaCommand from(final String aUuid) {

        return new ReadMetaLojaCommand(
                null,
                aUuid
        );
    }
}
