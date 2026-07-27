package com.hvs.webstore.back.domain.entity.webstore.metaloja;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.Optional;

public interface MetaLojaDomainGateway {

    MetaLoja create(MetaLoja aMetaLoja);

    Optional<MetaLoja> read(MetaLojaId aId);

    Optional<MetaLoja> readByUuid(MetaLojaUuid aUuid);

    Optional<MetaLoja> readByChave(String aChave);

    Pagination<MetaLoja> readAll(SearchQuery aQuery);

    MetaLoja update(MetaLoja aMetaLoja);

    MetaLoja patch(MetaLoja aMetaLoja);

    void delete(MetaLoja aMetaLoja);
}
