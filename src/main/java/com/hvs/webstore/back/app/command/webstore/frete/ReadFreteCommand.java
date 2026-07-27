package com.hvs.webstore.back.app.command.webstore.frete;

public record ReadFreteCommand(Long aId,
                               String aUuid) {

    public static ReadFreteCommand from(final Long aId) {

        return new ReadFreteCommand(
                aId,
                null
        );
    }

    public static ReadFreteCommand from(final String aUuid) {

        return new ReadFreteCommand(
                null,
                aUuid
        );
    }
}
