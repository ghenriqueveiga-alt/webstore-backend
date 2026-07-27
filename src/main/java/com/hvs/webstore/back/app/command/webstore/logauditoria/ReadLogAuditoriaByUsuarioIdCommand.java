package com.hvs.webstore.back.app.command.webstore.logauditoria;

public record ReadLogAuditoriaByUsuarioIdCommand(Long aUsuarioId) {

    public static ReadLogAuditoriaByUsuarioIdCommand from(final Long aUsuarioId) {

        return new ReadLogAuditoriaByUsuarioIdCommand(aUsuarioId);
    }
}
