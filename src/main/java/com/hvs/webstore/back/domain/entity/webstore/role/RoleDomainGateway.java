package com.hvs.webstore.back.domain.entity.webstore.role;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.Optional;

public interface RoleDomainGateway {

    Role create(Role aRole);

    Optional<Role> read(RoleId aId);

    Optional<Role> readByUuid(RoleUuid aUuid);

    Pagination<Role> readAll(SearchQuery aQuery);

    Optional<Role> readByNome(String aNome);

    Role update(Role aRole);

    Role patch(Role aRole);

    void delete(Role aRole);
}
