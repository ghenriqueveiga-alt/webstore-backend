package com.hvs.webstore.back.infra.persistence.webstore.tokenverificacao;

import com.hvs.webstore.back.domain.entity.webstore.tokenverificacao.TokenVerificacao;
import com.hvs.webstore.back.domain.entity.webstore.tokenverificacao.TokenVerificacaoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.tokenverificacao.TokenVerificacaoId;
import com.hvs.webstore.back.domain.entity.webstore.tokenverificacao.TokenVerificacaoUuid;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public class TokenVerificacaoDomainGatewayImpl implements TokenVerificacaoDomainGateway {

    private final TokenVerificacaoJpaRepository repository;

    public TokenVerificacaoDomainGatewayImpl(TokenVerificacaoJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public TokenVerificacao create(TokenVerificacao aTokenVerificacao) {

        return this.repository.save(TokenVerificacaoEntity.from(aTokenVerificacao)).toDomain();
    }

    @Override
    public Optional<TokenVerificacao> read(TokenVerificacaoId aId) {

        return this.repository.findById(aId.getValue()).map(TokenVerificacaoEntity::toDomain);
    }

    @Override
    public Optional<TokenVerificacao> readByUuid(TokenVerificacaoUuid aUuid) {

        return this.repository.findByUuid(aUuid.getValue()).map(TokenVerificacaoEntity::toDomain);
    }

    @Override
    public Optional<TokenVerificacao> readByToken(String aToken) {

        return this.repository.findByToken(aToken).map(TokenVerificacaoEntity::toDomain);
    }

    @Override
    public List<TokenVerificacao> readByUsuarioId(Long aUsuarioId) {

        return this.repository.findByUsuario_Id(aUsuarioId).stream().map(TokenVerificacaoEntity::toDomain).toList();
    }

    @Override
    public Pagination<TokenVerificacao> readAll(SearchQuery aQuery) {

        Page<TokenVerificacaoEntity> pages;
        Pageable pageable = PageRequest.of(
                aQuery.aPage(),
                aQuery.aSize(),
                Sort.Direction.fromString(aQuery.aDirection()),
                aQuery.aSort());

        if (aQuery.aSearch() != null && !aQuery.aSearch().trim().isEmpty()) {
            Specification<TokenVerificacaoEntity> specification =
                    (root, query, criteriaBuilder) -> {
                        String likePattern = "%" + aQuery.aSearch().toLowerCase() + "%";
                        return criteriaBuilder.like(criteriaBuilder.lower(root.get("token")), likePattern);
                    };

            pages = this.repository.findAll(specification, pageable);
        } else {
            pages = this.repository.findAll(pageable);
        }

        return new Pagination<>(
                pages.getNumber(),
                pages.getTotalElements(),
                pages.getTotalPages(),
                pages.getContent().stream().map(TokenVerificacaoEntity::toDomain).toList());
    }

    @Override
    public TokenVerificacao update(TokenVerificacao aTokenVerificacao) {

        return this.repository.save(TokenVerificacaoEntity.from(aTokenVerificacao)).toDomain();
    }

    @Override
    public TokenVerificacao patch(TokenVerificacao aTokenVerificacao) {

        return this.repository.save(TokenVerificacaoEntity.from(aTokenVerificacao)).toDomain();
    }

    @Override
    public void delete(TokenVerificacao aTokenVerificacao) {

        final var entity = TokenVerificacaoEntity.from(aTokenVerificacao);
        entity.setStatusDesc("Cancelled");
        this.repository.save(entity);
    }
}
