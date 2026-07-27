package com.hvs.webstore.back.app.command.webstore.cartao;

public record ReadCartaoCommand(Long aId,
                                String aUuid) {

    public static ReadCartaoCommand from(final Long aId) {

        return new ReadCartaoCommand(
                aId,
                null
        );
    }

    public static ReadCartaoCommand from(final String aUuid) {

        return new ReadCartaoCommand(
                null,
                aUuid
        );
    }
}
