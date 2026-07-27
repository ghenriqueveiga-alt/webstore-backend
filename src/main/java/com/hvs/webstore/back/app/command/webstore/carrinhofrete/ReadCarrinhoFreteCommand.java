package com.hvs.webstore.back.app.command.webstore.carrinhofrete;

public record ReadCarrinhoFreteCommand(Long aId,
                                       String aUuid) {

    public static ReadCarrinhoFreteCommand from(final Long aId) {

        return new ReadCarrinhoFreteCommand(
                aId,
                null
        );
    }

    public static ReadCarrinhoFreteCommand from(final String aUuid) {

        return new ReadCarrinhoFreteCommand(
                null,
                aUuid
        );
    }
}
