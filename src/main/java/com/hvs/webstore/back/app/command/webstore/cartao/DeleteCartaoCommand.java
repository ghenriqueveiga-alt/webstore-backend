package com.hvs.webstore.back.app.command.webstore.cartao;

public record DeleteCartaoCommand(Long aId,
                                  String aUuid) {

    public static DeleteCartaoCommand from(final Long aId) {

        return new DeleteCartaoCommand(
                aId,
                null);
    }

    public static DeleteCartaoCommand from(final String aUuid) {

        return new DeleteCartaoCommand(
                null,
                aUuid);
    }
}
