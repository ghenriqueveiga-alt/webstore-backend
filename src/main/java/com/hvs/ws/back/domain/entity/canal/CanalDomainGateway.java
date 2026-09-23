package com.hvs.ws.back.domain.entity.canal;

import com.hvs.ws.back.app.command.canal.CanalSearchQuery;
import com.hvs.ws.back.domain.pagination.Pagination;

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
