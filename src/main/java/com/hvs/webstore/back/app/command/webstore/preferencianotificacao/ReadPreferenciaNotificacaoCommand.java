package com.hvs.webstore.back.app.command.webstore.preferencianotificacao;

public record ReadPreferenciaNotificacaoCommand(Long aId,
                                                String aUuid) {

    public static ReadPreferenciaNotificacaoCommand from(final Long aId) {

        return new ReadPreferenciaNotificacaoCommand(
                aId,
                null
        );
    }

    public static ReadPreferenciaNotificacaoCommand from(final String aUuid) {

        return new ReadPreferenciaNotificacaoCommand(
                null,
                aUuid
        );
    }
}
