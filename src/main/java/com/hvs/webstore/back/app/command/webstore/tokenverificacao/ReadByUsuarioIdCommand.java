package com.hvs.webstore.back.app.command.webstore.tokenverificacao;

public record ReadByUsuarioIdCommand(Long aUsuarioId) {

    public static ReadByUsuarioIdCommand from(final Long aUsuarioId) {

        return new ReadByUsuarioIdCommand(aUsuarioId);
    }
}
