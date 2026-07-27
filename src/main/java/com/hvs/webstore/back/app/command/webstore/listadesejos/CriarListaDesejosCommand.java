package com.hvs.webstore.back.app.command.webstore.listadesejos;

public record CriarListaDesejosCommand(Long usuarioId) {

    public static CriarListaDesejosCommand from(final Long usuarioId) {

        return new CriarListaDesejosCommand(usuarioId);
    }
}
