package com.hvs.webstore.back.app.command.webstore.pix;

public record ReadPixCommand(Long aId,
                             String aUuid) {

    public static ReadPixCommand from(final Long aId) {

        return new ReadPixCommand(
                aId,
                null
        );
    }

    public static ReadPixCommand from(final String aUuid) {

        return new ReadPixCommand(
                null,
                aUuid
        );
    }
}
