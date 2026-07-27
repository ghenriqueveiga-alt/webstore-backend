package com.hvs.webstore.back.app.command.webstore.cupom;

public record ReadCupomCommand(Long aId,
                               String aUuid) {

    public static ReadCupomCommand from(final Long aId) {

        return new ReadCupomCommand(
                aId,
                null
        );
    }

    public static ReadCupomCommand from(final String aUuid) {

        return new ReadCupomCommand(
                null,
                aUuid
        );
    }
}
