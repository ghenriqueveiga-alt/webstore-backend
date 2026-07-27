package com.hvs.webstore.back.app.command.webstore.preferencianotificacao;

public record CreatePreferenciaNotificacaoCommand(Long aUsuarioId,
                                                  String aTipoCode,
                                                  Boolean aAtivo) {

    public static CreatePreferenciaNotificacaoCommand from(final Long aUsuarioId,
                                                           final String aTipoCode,
                                                           final Boolean aAtivo) {

        return new CreatePreferenciaNotificacaoCommand(
                aUsuarioId,
                aTipoCode,
                aAtivo);
    }
}
