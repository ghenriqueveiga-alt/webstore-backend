package com.hvs.webstore.back.app.command.webstore.video;

public record ReadVideoCommand(Long aId,
                               String aUuid) {

    public static ReadVideoCommand from(final Long aId) {

        return new ReadVideoCommand(
                aId,
                null
        );
    }

    public static ReadVideoCommand from(final String aUuid) {

        return new ReadVideoCommand(
                null,
                aUuid
        );
    }
}
