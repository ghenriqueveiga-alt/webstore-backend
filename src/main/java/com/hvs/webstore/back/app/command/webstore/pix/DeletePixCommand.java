package com.hvs.webstore.back.app.command.webstore.pix;

public record DeletePixCommand(Long aId,
                               String aUuid) {

    public static DeletePixCommand from(final Long aId) {

        return new DeletePixCommand(
                aId,
                null);
    }

    public static DeletePixCommand from(final String aUuid) {

        return new DeletePixCommand(
                null,
                aUuid);
    }
}
