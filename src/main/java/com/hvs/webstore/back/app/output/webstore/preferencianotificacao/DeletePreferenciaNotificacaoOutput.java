package com.hvs.webstore.back.app.output.webstore.preferencianotificacao;

import com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao.PreferenciaNotificacao;

public record DeletePreferenciaNotificacaoOutput(Long aId,
                                                 String aUuid,
                                                 String aMessage) {

    public static DeletePreferenciaNotificacaoOutput from(PreferenciaNotificacao aPreferenciaNotificacao) {

        return new DeletePreferenciaNotificacaoOutput(
                aPreferenciaNotificacao.getId().getValue(),
                aPreferenciaNotificacao.getUuid().getValue(),
                "The PreferenciaNotificacao with id: " + aPreferenciaNotificacao.getUuid().getValue() + " has been successfully deleted.");
    }
}
