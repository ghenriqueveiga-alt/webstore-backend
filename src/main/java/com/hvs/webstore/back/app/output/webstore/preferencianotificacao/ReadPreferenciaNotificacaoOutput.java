package com.hvs.webstore.back.app.output.webstore.preferencianotificacao;

import com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao.PreferenciaNotificacao;

public record ReadPreferenciaNotificacaoOutput(Long aId,
                                               String aUuid,
                                               String aStatusDesc,
                                               Long aUsuarioId,
                                               String aTipoCode,
                                               Boolean aAtivo) {

    public static ReadPreferenciaNotificacaoOutput from(PreferenciaNotificacao aPreferenciaNotificacao) {

        return new ReadPreferenciaNotificacaoOutput(
                aPreferenciaNotificacao.getId().getValue(),
                aPreferenciaNotificacao.getUuid().getValue(),
                aPreferenciaNotificacao.getStatusCode().getDesc(),
                aPreferenciaNotificacao.getUsuario() != null ? aPreferenciaNotificacao.getUsuario().getId().getValue() : null,
                aPreferenciaNotificacao.getTipo().getCode(),
                aPreferenciaNotificacao.getAtivo());
    }

    public static ReadPreferenciaNotificacaoOutput fromSimple(PreferenciaNotificacao aPreferenciaNotificacao) {

        return new ReadPreferenciaNotificacaoOutput(
                aPreferenciaNotificacao.getId().getValue(),
                aPreferenciaNotificacao.getUuid().getValue(),
                null,
                null,
                null,
                null);
    }
}
