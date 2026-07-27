package com.hvs.webstore.back.app.command.webstore.cupom;

public record DeleteCupomCommand(Long aId,
                                 String aUuid) {

    public static DeleteCupomCommand from(final Long aId) {

        return new DeleteCupomCommand(
                aId,
                null);
    }

    public static DeleteCupomCommand from(final String aUuid) {

        return new DeleteCupomCommand(
                null,
                aUuid);
    }
}
