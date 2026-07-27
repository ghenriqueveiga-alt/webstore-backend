package com.hvs.webstore.back.app.command.webstore.preferencianotificacao;

public record DeletePreferenciaNotificacaoCommand(Long aId,
                                                  String aUuid) {

    public static DeletePreferenciaNotificacaoCommand from(final Long aId) {

        return new DeletePreferenciaNotificacaoCommand(
                aId,
                null);
    }

    public static DeletePreferenciaNotificacaoCommand from(final String aUuid) {

        return new DeletePreferenciaNotificacaoCommand(
                null,
                aUuid);
    }
}
