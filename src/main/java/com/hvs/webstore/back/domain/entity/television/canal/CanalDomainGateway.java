package com.hvs.webstore.back.domain.entity.television.canal;

import com.hvs.webstore.back.app.command.television.canal.CanalSearchQuery;
import com.hvs.webstore.back.domain.pagination.Pagination;

import java.util.Optional;

public interface CanalDomainGateway {

    Canal create(Canal aCanal);

    Optional<Canal> read(CanalId aId);

    Optional<Canal> readByUuid(CanalUuid aUuid);

    Pagination<Canal> readAll(CanalSearchQuery aQuery);

    Canal update(Canal aCanal);

    Canal patch(Canal aCanal);

    void delete(Canal aCanal);
}
