package com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

public interface PreferenciaNotificacaoDomainGateway {

    PreferenciaNotificacao create(PreferenciaNotificacao aPreferenciaNotificacao);

    Optional<PreferenciaNotificacao> read(PreferenciaNotificacaoId aId);

    Optional<PreferenciaNotificacao> readByUuid(PreferenciaNotificacaoUuid aUuid);

    Pagination<PreferenciaNotificacao> readAll(SearchQuery aQuery);

    List<PreferenciaNotificacao> readByUsuarioId(Long aUsuarioId);

    Optional<PreferenciaNotificacao> readByUsuarioIdAndTipo(Long aUsuarioId, String aTipoCode);

    PreferenciaNotificacao update(PreferenciaNotificacao aPreferenciaNotificacao);

    PreferenciaNotificacao patch(PreferenciaNotificacao aPreferenciaNotificacao);

    void delete(PreferenciaNotificacao aPreferenciaNotificacao);
}
