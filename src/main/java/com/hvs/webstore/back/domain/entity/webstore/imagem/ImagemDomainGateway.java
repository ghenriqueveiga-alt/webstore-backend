package com.hvs.webstore.back.domain.entity.webstore.imagem;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.Optional;

public interface ImagemDomainGateway {

    Imagem create(Imagem aImagem);

    Optional<Imagem> read(ImagemId aId);

    Optional<Imagem> readByUuid(ImagemUuid aUuid);

    Pagination<Imagem> readAll(SearchQuery aQuery);

    Imagem update(Imagem aImagem);

    Imagem patch(Imagem aImagem);

    void delete(Imagem aImagem);
}
