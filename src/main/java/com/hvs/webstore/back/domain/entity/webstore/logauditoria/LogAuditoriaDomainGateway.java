package com.hvs.webstore.back.domain.entity.webstore.logauditoria;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

public interface LogAuditoriaDomainGateway {

    LogAuditoria create(LogAuditoria aLogAuditoria);

    Optional<LogAuditoria> read(LogAuditoriaId aId);

    Optional<LogAuditoria> readByUuid(LogAuditoriaUuid aUuid);

    Pagination<LogAuditoria> readAll(SearchQuery aQuery);

    List<LogAuditoria> readByEntidade(String entidadeNome);

    List<LogAuditoria> readByUsuarioId(Long aUsuarioId);

    LogAuditoria update(LogAuditoria aLogAuditoria);

    LogAuditoria patch(LogAuditoria aLogAuditoria);

    void delete(LogAuditoria aLogAuditoria);
}
