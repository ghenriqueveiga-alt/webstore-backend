package com.hvs.webstore.back.app.command.webstore.preferencianotificacao;

public record UpdatePreferenciaNotificacaoCommand(Long aId,
                                                  String aUuid,
                                                  String aStatusCode,
                                                  Long aUsuarioId,
                                                  String aTipoCode,
                                                  Boolean aAtivo) {

    public static UpdatePreferenciaNotificacaoCommand from(final Long aId,
                                                           final UpdatePreferenciaNotificacaoCommand aIn) {

        return new UpdatePreferenciaNotificacaoCommand(
                aId,
                null,
                aIn.aStatusCode,
                aIn.aUsuarioId,
                aIn.aTipoCode,
                aIn.aAtivo
        );
    }

    public static UpdatePreferenciaNotificacaoCommand from(final String aUuid,
                                                           final UpdatePreferenciaNotificacaoCommand aIn) {

        return new UpdatePreferenciaNotificacaoCommand(
                null,
                aUuid,
                aIn.aStatusCode,
                aIn.aUsuarioId,
                aIn.aTipoCode,
                aIn.aAtivo
        );
    }
}
