package com.hvs.webstore.back.app.output.webstore.preferencianotificacao;

import com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao.PreferenciaNotificacao;

public record PatchPreferenciaNotificacaoOutput(Long aId,
                                                String aUuid,
                                                String aMessage) {

    public static PatchPreferenciaNotificacaoOutput from(PreferenciaNotificacao aPreferenciaNotificacao) {

        return new PatchPreferenciaNotificacaoOutput(
                aPreferenciaNotificacao.getId().getValue(),
                aPreferenciaNotificacao.getUuid().getValue(),
                "The PreferenciaNotificacao with id: " + aPreferenciaNotificacao.getUuid().getValue() + " has been successfully patched.");
    }
}
