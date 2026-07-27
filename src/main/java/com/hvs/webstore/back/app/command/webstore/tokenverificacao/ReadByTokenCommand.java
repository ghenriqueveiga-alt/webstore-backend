package com.hvs.webstore.back.app.command.webstore.tokenverificacao;

public record ReadByTokenCommand(String aToken) {

    public static ReadByTokenCommand from(final String aToken) {

        return new ReadByTokenCommand(aToken);
    }
}
