package com.hvs.webstore.back.domain.entity.webstore.permissao;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.Optional;

public interface PermissaoDomainGateway {

    Permissao create(Permissao aPermissao);

    Optional<Permissao> read(PermissaoId aId);

    Optional<Permissao> readByUuid(PermissaoUuid aUuid);

    Pagination<Permissao> readAll(SearchQuery aQuery);

    Optional<Permissao> readByChave(String aChave);

    Permissao update(Permissao aPermissao);

    Permissao patch(Permissao aPermissao);

    void delete(Permissao aPermissao);
}
