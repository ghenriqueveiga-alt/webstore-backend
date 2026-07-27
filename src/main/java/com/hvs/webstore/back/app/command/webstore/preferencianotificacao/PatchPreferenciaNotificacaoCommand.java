package com.hvs.webstore.back.app.command.webstore.preferencianotificacao;

public record PatchPreferenciaNotificacaoCommand(Long aId,
                                                 String aUuid,
                                                 String aStatusCode,
                                                 Long aUsuarioId,
                                                 String aTipoCode,
                                                 Boolean aAtivo) {

    public static PatchPreferenciaNotificacaoCommand from(final Long aId,
                                                          final PatchPreferenciaNotificacaoCommand aIn) {

        return new PatchPreferenciaNotificacaoCommand(
                aId,
                null,
                aIn.aStatusCode,
                aIn.aUsuarioId,
                aIn.aTipoCode,
                aIn.aAtivo);
    }

    public static PatchPreferenciaNotificacaoCommand from(final String aUuid,
                                                          final PatchPreferenciaNotificacaoCommand aIn) {

        return new PatchPreferenciaNotificacaoCommand(
                null,
                aUuid,
                aIn.aStatusCode,
                aIn.aUsuarioId,
                aIn.aTipoCode,
                aIn.aAtivo);
    }
}
