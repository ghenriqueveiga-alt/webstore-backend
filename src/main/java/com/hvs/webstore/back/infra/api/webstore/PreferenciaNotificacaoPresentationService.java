package com.hvs.webstore.back.infra.api.webstore;

import com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao.PreferenciaNotificacao;
import com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao.PreferenciaNotificacaoDomainGateway;

import java.util.List;

public class PreferenciaNotificacaoPresentationService {
    private final PreferenciaNotificacaoDomainGateway gateway;
    public PreferenciaNotificacaoPresentationService(PreferenciaNotificacaoDomainGateway gateway) { this.gateway = gateway; }

    public List<PreferenciaNotificacao> readByUsuarioId(Long usuarioId) { return gateway.readByUsuarioId(usuarioId); }
    public java.util.Optional<PreferenciaNotificacao> readByUsuarioIdAndTipo(Long usuarioId, String tipo) { return gateway.readByUsuarioIdAndTipo(usuarioId, tipo); }
}
