package com.hvs.webstore.back.app.command.webstore.metaloja;

public record DeleteMetaLojaCommand(Long aId,
                                    String aUuid) {

    public static DeleteMetaLojaCommand from(final Long aId) {

        return new DeleteMetaLojaCommand(
                aId,
                null);
    }

    public static DeleteMetaLojaCommand from(final String aUuid) {

        return new DeleteMetaLojaCommand(
                null,
                aUuid);
    }
}
