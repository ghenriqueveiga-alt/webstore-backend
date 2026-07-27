package com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

public interface NotificacaoTemplateDomainGateway {

    NotificacaoTemplate create(NotificacaoTemplate aNotificacaoTemplate);

    Optional<NotificacaoTemplate> read(NotificacaoTemplateId aId);

    Optional<NotificacaoTemplate> readByUuid(NotificacaoTemplateUuid aUuid);

    List<NotificacaoTemplate> readByTipo(String aTipoCode);

    Pagination<NotificacaoTemplate> readAll(SearchQuery aQuery);

    NotificacaoTemplate update(NotificacaoTemplate aNotificacaoTemplate);

    NotificacaoTemplate patch(NotificacaoTemplate aNotificacaoTemplate);

    void delete(NotificacaoTemplate aNotificacaoTemplate);
}
