package com.hvs.webstore.back.app.output.webstore.preferencianotificacao;

import com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao.PreferenciaNotificacao;

public record UpdatePreferenciaNotificacaoOutput(Long aId,
                                                  String aUuid,
                                                  String aMessage) {

    public static UpdatePreferenciaNotificacaoOutput from(PreferenciaNotificacao aPreferenciaNotificacao) {

        return new UpdatePreferenciaNotificacaoOutput(
                aPreferenciaNotificacao.getId().getValue(),
                aPreferenciaNotificacao.getUuid().getValue(),
                "The PreferenciaNotificacao with id: " + aPreferenciaNotificacao.getUuid().getValue() + " has been successfully updated.");
    }
}
