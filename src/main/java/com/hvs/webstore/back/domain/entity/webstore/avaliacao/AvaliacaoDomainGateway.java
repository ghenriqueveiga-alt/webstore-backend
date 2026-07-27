package com.hvs.webstore.back.domain.entity.webstore.avaliacao;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.Optional;

public interface AvaliacaoDomainGateway {

    Avaliacao create(Avaliacao aAvaliacao);

    Optional<Avaliacao> read(AvaliacaoId aId);

    Optional<Avaliacao> readByUuid(AvaliacaoUuid aUuid);

    Pagination<Avaliacao> readAll(SearchQuery aQuery);

    Pagination<Avaliacao> readByProduto(Long aProdutoId, SearchQuery aQuery);

    Avaliacao update(Avaliacao aAvaliacao);

    Avaliacao patch(Avaliacao aAvaliacao);

    void delete(Avaliacao aAvaliacao);
}
