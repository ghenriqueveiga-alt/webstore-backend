package com.hvs.webstore.back.domain.entity.webstore.pix;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.Optional;

public interface PixDomainGateway {

    Pix create(Pix aPix);

    Optional<Pix> read(PixId aId);

    Optional<Pix> readByUuid(PixUuid aUuid);

    Pagination<Pix> readAll(SearchQuery aQuery);

    Pix update(Pix aPix);

    Pix patch(Pix aPix);

    void delete(Pix aPix);
}
