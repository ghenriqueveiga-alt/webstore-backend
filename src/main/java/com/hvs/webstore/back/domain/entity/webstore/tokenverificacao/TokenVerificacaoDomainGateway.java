package com.hvs.webstore.back.domain.entity.webstore.tokenverificacao;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

public interface TokenVerificacaoDomainGateway {

    TokenVerificacao create(TokenVerificacao aTokenVerificacao);

    Optional<TokenVerificacao> read(TokenVerificacaoId aId);

    Optional<TokenVerificacao> readByUuid(TokenVerificacaoUuid aUuid);

    Pagination<TokenVerificacao> readAll(SearchQuery aQuery);

    Optional<TokenVerificacao> readByToken(String aToken);

    List<TokenVerificacao> readByUsuarioId(Long aUsuarioId);

    TokenVerificacao update(TokenVerificacao aTokenVerificacao);

    TokenVerificacao patch(TokenVerificacao aTokenVerificacao);

    void delete(TokenVerificacao aTokenVerificacao);
}
