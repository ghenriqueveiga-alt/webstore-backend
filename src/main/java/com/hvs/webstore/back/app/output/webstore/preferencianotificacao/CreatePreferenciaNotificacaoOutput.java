package com.hvs.webstore.back.app.output.webstore.preferencianotificacao;

import com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao.PreferenciaNotificacao;

public record CreatePreferenciaNotificacaoOutput(Long aId,
                                                 String aUuid,
                                                 String aMessage) {

    public static CreatePreferenciaNotificacaoOutput from(PreferenciaNotificacao aPreferenciaNotificacao) {

        return new CreatePreferenciaNotificacaoOutput(
                aPreferenciaNotificacao.getId().getValue(),
                aPreferenciaNotificacao.getUuid().getValue(),
                "The PreferenciaNotificacao with id: " + aPreferenciaNotificacao.getUuid().getValue() + " has been successfully created.");
    }
}
